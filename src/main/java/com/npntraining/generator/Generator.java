package com.npntraining.generator;

import java.util.List;

import com.npntraining.model.DataModelGenerator;
import com.npntraining.model.TableType.Columns;
import com.npntraining.model.TableType.Columns.Column;

public interface Generator {
	public static String generateDeleteQuery(DataModelGenerator dataModelGenerator) {
		String tableName = dataModelGenerator.getTable().get(0).getName();
		Columns columns = dataModelGenerator.getTable().get(0).getColumns();

		if (columns == null || columns.getColumn() == null || columns.getColumn().isEmpty()) {
			String dmlStatement = "DROP TABLE " + tableName + ";";
			return dmlStatement;
		} else {
			List<Column> columnList = dataModelGenerator.getTable().get(0).getColumns().getColumn();

			String initialColumnString = "";
			for (int i = 0; i < columnList.size(); i++) {
				if (i == 0) {
					initialColumnString = "(" + columnList.get(i).getName() + " "
							+ extractType(columnList.get(i).getType()) + ", ";
				} else if (i == columnList.size() - 1) {
					initialColumnString += columnList.get(i).getName() + " " + extractType(columnList.get(i).getType())
							+ ")";
				} else {
					initialColumnString += columnList.get(i).getName() + " " + extractType(columnList.get(i).getType())
							+ ", ";
				}
			}
			String dmlStatement = ("ALTER TABLE " + tableName + " REPLACE COLUMNS " + initialColumnString + ";");
			return dmlStatement;
		}
	}

	private static String extractType(String type) {
		String returnValue = type;
		if (returnValue.contains("=")) {
			returnValue = returnValue.replace("=", "<");
			returnValue = returnValue + ">";
		}
		return returnValue.replaceAll("\\|", ",");
	}
}
