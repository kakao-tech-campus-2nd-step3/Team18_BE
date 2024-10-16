package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import team18.team18_be.contract.dto.request.ContractRequest;

@Service
public class ContractPdfService {

  public byte[] createPdf(ContractRequest request)
      throws DocumentException, IOException {

    // 원본 파일 읽기
    ClassPathResource resource = new ClassPathResource("contract.pdf");
    PdfReader reader = new PdfReader(resource.getInputStream());

    // 메모리 안에 PDF 파일 생성
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PdfStamper stamper = new PdfStamper(reader, byteArrayOutputStream);

    // pdf 수정할 페이지 지정
    PdfContentByte contentByte = stamper.getOverContent(1);

    // 폰트 지정
    BaseFont font = BaseFont.createFont("gothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    contentByte.setFontAndSize(font, 10);

    // 글자 추가
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, Integer.toString(request.salary()), 350,
        630, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER,
        request.contractPeriod().startDate() + " ~ " + request.contractPeriod().endDate(), 350, 550,
        0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.dayOff(), 350, 475, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.annualPaidLeave(), 350, 400, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER,
        request.workingPlace() + " / " + request.responsibilities(), 350, 320, 0);
    contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.rule(), 350, 250, 0);

    // 서명 추가


    stamper.close();
    reader.close();

    return byteArrayOutputStream.toByteArray();
  }

}
