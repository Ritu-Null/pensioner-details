package com.digitalhonors.pensionerdetail.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.digitalhonors.pensionerdetail.exception.InvalidDataFormatException;
import com.digitalhonors.pensionerdetail.model.PensionerDetail;
import com.digitalhonors.pensionerdetail.repository.PensionerRepository;

public class CSVHelper {
	private final static String panRegex = "([A-Z]){5}([0-9]){4}([A-Z]){1}$";
	private final static String amountRegex = "^[0-9]*$";
	private final static String aadharRegex = "^\\d{10}$";
	private final static String accountNumberRegex = "^\\d{12}$";
	private final static String bankTypeRegex = "^.*(private|public|PRIVATE|PUBLIC|Private|Public).*$";
	private final static String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
	private final static String pensionType = "^.*(family|self|FAMILY|Family|SELF|Self).*$";
	private final static String dateRegex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";

	@Autowired
	private static PensionerRepository pensionerRepository;

	private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CSVHelper.class);
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Aadhar Number", "Name", "PAN", "Salary Earned", "Allowance", "Pension Type" };

	public static boolean hasCSVFormat(MultipartFile file) {
		logger.info("Checking File format.");
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public static List<PensionerDetail> csvToPensionerDetails(InputStream is)
			throws FileNotFoundException, IOException, InvalidDataFormatException {

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
			List<PensionerDetail> pensionerDetails = new ArrayList<PensionerDetail>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

				PensionerDetail pensionerDetail;
				

				try {

					if (Pattern.matches(aadharRegex, csvRecord.get("Aadhar Number"))
							&& Pattern.matches(nameRegex, csvRecord.get("Name"))
							&& Pattern.matches(dateRegex, csvRecord.get("DOB"))
							&& Pattern.matches(panRegex, csvRecord.get("PAN"))
							&& Pattern.matches(amountRegex, csvRecord.get("Salary Earned"))
							&& Pattern.matches(amountRegex, csvRecord.get("Allowance"))
							&& Pattern.matches(pensionType, csvRecord.get("Pension Type"))
							&& Pattern.matches(nameRegex, csvRecord.get("Bank Name"))
							&& Pattern.matches(accountNumberRegex, csvRecord.get("Account Number")) &&

							Pattern.matches(bankTypeRegex, csvRecord.get("Bank Type"))) {

						
					System.out.println(csvRecord.get("Aadhar Number"));
					double aaadhar=Double.parseDouble(csvRecord.get("Aadhar Number"));

					
							pensionerDetail = new PensionerDetail(

									Double.parseDouble(csvRecord.get("Aadhar Number")), csvRecord.get("Name"),
									formatter.parse(csvRecord.get("DOB")), csvRecord.get("PAN"),
									Double.parseDouble(csvRecord.get("Salary Earned")),
									Double.parseDouble(csvRecord.get("Allowance")), csvRecord.get("Pension Type"),
									csvRecord.get("Bank Name"), Double.parseDouble(csvRecord.get("Account Number")),
									csvRecord.get("Bank type"));

									pensionerDetails.add(pensionerDetail);

						

					} else {
						logger.error("InvalidDataFormatException: Data field not in correct format");
						throw new InvalidDataFormatException("The data format is not correct.");
					}
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					logger.error("Exception thrown: " + e.getMessage());
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					logger.error("Exception thrown: " + e.getMessage());
					e.printStackTrace();
				}

			}

			return pensionerDetails;
		} catch (IOException e) {
			logger.error("Failed to pasrse CSV file: " + e.getMessage());
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

}