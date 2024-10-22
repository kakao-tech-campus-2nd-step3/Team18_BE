package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import team18.team18_be.auth.entity.User;
import team18.team18_be.contract.dto.request.ContractRequest;

@Service
public class ContractPdfService {

  private final ContractFileService contractFileService;

  public ContractPdfService(ContractFileService contractFileService) {
    this.contractFileService = contractFileService;
  }

  public byte[] createPdf(ContractRequest request, User user)
      throws DocumentException, IOException {

    // 원본 파일 읽기
    ClassPathResource resource = new ClassPathResource("contract.pdf");
    // 메모리 안에 PDF 파일을 담기 위한 스트림
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    // PDF 준비
    PdfContentByte contentByte = preparePdfContent(resource.getContentAsByteArray(),
        byteArrayOutputStream);

    // 폰트 준비
    BaseFont font = prepareFont();
    contentByte.setFontAndSize(font, 10);

    // 글자 추가
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, Integer.toString(request.salary()), 350,
        630, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER,
        request.workingHours(), 350, 550,
        0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.dayOff(), 350, 475, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.annualPaidLeave(), 350, 400, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER,
        request.workingPlace() + " / " + request.responsibilities(), 350, 320, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.rule(), 350, 250, 0);

    // 서명 추가
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, user.getName(), 457, 123, 0);

    // 고용주 서명 받기
    byte[] imageBytes = contractFileService.getSignImage(user);

    // 서명 이미지 추가
    addImageToPdf(contentByte, imageBytes, 50, 50, 465, 103);

    return byteArrayOutputStream.toByteArray();
  }

  public byte[] fillInEmployeeSign(byte[] pdfData, User user)
      throws IOException, DocumentException {

    // 메모리 안에 PDF 파일을 담기 위한 스트림
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    // PDF 준비
    PdfContentByte contentByte = preparePdfContent(pdfData, byteArrayOutputStream);

    // 폰트 준비
    BaseFont font = prepareFont();
    contentByte.setFontAndSize(font, 10);

    // 서명 추가
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, user.getName(), 457, 113, 0);

    // 근로자 서명 받기
    byte[] imageBytes = contractFileService.getSignImage(user);

    // 근로자 서명 이미지 추가
    addImageToPdf(contentByte, imageBytes, 50, 50, 465, 83);

    return byteArrayOutputStream.toByteArray();
  }

  private PdfContentByte preparePdfContent(byte[] pdfData,
      ByteArrayOutputStream byteArrayOutputStream) throws IOException, DocumentException {

    PdfReader reader = new PdfReader(pdfData);

    // 메모리 안에 PDF 파일 생성
    PdfStamper stamper = new PdfStamper(reader, byteArrayOutputStream);

    // pdf 수정할 페이지 지정
    return stamper.getOverContent(1);
  }

  private BaseFont prepareFont() throws DocumentException, IOException {

    // 폰트 지정
    return BaseFont.createFont("gothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
  }

  private void addImageToPdf(PdfContentByte contentByte, byte[] imageBytes, int width, int height,
      int x, int y) throws IOException, DocumentException {
    Image image = Image.getInstance(imageBytes);
    image.scaleToFit(width, height);
    image.setAbsolutePosition(x, y);
    contentByte.addImage(image);
  }

}
