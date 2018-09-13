package com.npntraining.run;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.npntraining.generator.Generator;
import com.npntraining.model.DataModelGenerator;
/**
 * 
 * @author shruthimadumbu
 *
 */
public class RunReadXml {

	public static void main(String[] args) throws JAXBException {
		DataModelGenerator dataModelGenerator = null;
		File file = new File("src/main/resources/final_proposal_delete.xml");
		JAXBContext jaxbContext = JAXBContext
				.newInstance(DataModelGenerator.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		dataModelGenerator = (DataModelGenerator) unmarshaller
				.unmarshal(file);
		String query = Generator.generateDeleteQuery(dataModelGenerator);
		System.out.println(query);
	}

}


// ALTER TABLE Customer REPLACE COLUMNS (imsi BIGINT, name ARRAY<String>, deductions MAP<String,String>);
// dataModelGenerator.getTable().get(0).getName() -- Gives Name
// We need to fetch multiple column values (We cannot simply use get(0))
// dataModelGenerator.getTable().get(0).getColumns().getColumn().get(0).getName() -- Gives Column name
// dataModelGenerator.getTable().get(0).getColumns().getColumn().get(0).getType() -- Give Type