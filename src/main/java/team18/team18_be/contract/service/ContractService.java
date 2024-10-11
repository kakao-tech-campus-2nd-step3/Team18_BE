package team18.team18_be.contract.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import team18.team18_be.auth.entity.User;
import team18.team18_be.contract.dto.request.ContractRequest;
import team18.team18_be.contract.dto.response.ContractResponse;
import team18.team18_be.contract.repository.ContractRepository;

import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class ContractService {

    private final ContractRepository contractRepository;

    public ContractService(ContractRepository contractRepository) {
        this.contractRepository = contractRepository;
    }

    public void makeContract(ContractRequest request, User user) throws DocumentException, IOException {

        // 원본 파일 읽기
        ClassPathResource resource = new ClassPathResource("contract.pdf");
        PdfReader reader = new PdfReader(resource.getInputStream());

        // 수정된 파일 이름 : 고용주 id + 근로자 id
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(user.getId() + "_" + request.employeeId() + ".pdf"));

        // pdf 수정할 페이지 지정
        PdfContentByte contentByte = stamper.getOverContent(1);

        // 폰트 지정
        BaseFont font = BaseFont.createFont("gothic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        contentByte.setFontAndSize(font, 10);

        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, Integer.toString(request.salary()), 350, 630, 0);
        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.contractPeriod().startDate() + " ~ " + request.contractPeriod().endDate(), 350, 550, 0);
        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.dayOff(), 350, 475, 0);
        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.annualPaidLeave(), 350, 400, 0);
        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.workingPlace() + " / " + request.responsibilities(), 350, 320, 0);
        contentByte.showTextAligned(Paragraph.ALIGN_CENTER, request.rule(), 350, 250, 0);

        stamper.close();
        reader.close();
    }

    public void makeContractEmployee(User user) {
    }

    public ContractResponse getContract(User user) {
        return new ContractResponse(1L, "");
    }

    public String downloadContract(Long id, User user) {
        return "";
    }

    public String previewContract(Long id, User user) {
        return "";
    }
}
