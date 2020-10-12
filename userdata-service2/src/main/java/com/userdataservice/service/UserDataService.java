package com.userdataservice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.userserviceservice.model.User;
import com.userserviceservice.model.UserDto;

@Service
public class UserDataService implements UserDataIF {

	private static Logger logger = LoggerFactory.getLogger(UserDataService.class);

	private static final String CSV_SEPARATOR = ",";

	private static final String CSV = "CSV";

	@Override
	public void updateUser(User user) {
		if (user.getFileType().equals(CSV)) {
			updateCSV(user);
		} else {
			updateXML(user);
		}

	}

	private void updateXML(User user) {
		File xmlFile = new File(user.getUserId() + ".xml");
		try {

			User userXml = readXmlFileForUserId(xmlFile);

			if (null != userXml) {
				writeToXML(user);
			}

		} catch (JAXBException e) {
			logger.info("XML file with userID : " + user.getUserId() + " is not available for update");
		}
	}

	private User readXmlFileForUserId(File xmlFile) throws JAXBException {

		JAXBContext jaxbContext;

		jaxbContext = JAXBContext.newInstance(User.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		User userXml = (User) jaxbUnmarshaller.unmarshal(xmlFile);

		return userXml;
	}

	private void updateCSV(User user) {
		{
			String line = "";
			try {
				BufferedReader br = new BufferedReader(new FileReader(user.getUserId() + ".csv"));
				while ((line = br.readLine()) != null) {
					writeToCSV(user);
				}
			} catch (IOException e) {
				logger.info("CSV file with userID : " + user.getUserId() + " is not available for update");
			}
		}
	}

	@Override
	public void saveUser(User user) {
		if (user.getFileType().equals(CSV)) {
			writeToCSV(user);
		} else {
			writeToXML(user);
		}

	}

	private static void writeToXML(User user) {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);

			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File(user.getUserId() + ".xml");

			jaxbMarshaller.marshal(user, file);

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void writeToCSV(User user) {
		try {
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(user.getUserId() + ".csv"), "UTF-8"));

			StringBuffer oneLine = new StringBuffer();
			oneLine.append(user.getUserId());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(user.getName());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(user.getDateOfBirth());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(user.getSalary());
			oneLine.append(CSV_SEPARATOR);
			oneLine.append(user.getAge());
			bw.write(oneLine.toString());
			bw.newLine();

			bw.flush();
			bw.close();
		} catch (UnsupportedEncodingException e) {
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}

	@Override
	public UserDto readUser(String userId) {
		File fileCsv = new File(userId + ".csv");
		if (fileCsv.exists()) {
			logger.info("Exists");
			String line = "";
			String splitBy = ",";
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileCsv+".csv"));
				while ((line = br.readLine()) != null) 
				{
					String[] user = line.split(splitBy); 
					UserDto userDto = new UserDto();
					userDto.setUserId(Integer.parseInt(user[0]));
					
					/*
					 * System.out.println("Employee [First Name=" + employee[0] + ", Last Name=" +
					 * employee[1] + ", Designation=" + employee[2] + ", Contact=" + employee[3] +
					 * ", Salary= " + employee[4] + ", City= " + employee[5] + "]");
					 */
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			File fileXml = new File(userId + ".xml");
			if (fileXml.exists()) {
				try {
					User user = readXmlFileForUserId(fileXml);
					return converToDto(user);
				} catch (JAXBException e) {
					e.printStackTrace();
				}
				logger.info("Exists");
			} else {
				logger.info("CSV/XML file for userID : " + userId + " does not Exists");
			}

		}
		return null;
	}

	private UserDto converToDto(User user) {
		// TODO Auto-generated method stub
		UserDto dto = new UserDto();
		dto.setUserId(user.getUserId());
		dto.setAge(user.getAge());
		dto.setName(user.getName());
		dto.setSalary(user.getSalary());
		dto.setDateOfBirth(user.getDateOfBirth());
		return dto;
	}

}
