package com.sap.iot.ch.slack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SlackUtil {

	public enum FABI_TYPE {
		home {
			public String toString() {
				return "\"Home Office\"";
			}
		},
		office {
			public String toString() {
				return "\"At SAP\"";
			}
		},
		customer {
			public String toString() {
				return "\"Not at SAP\"";
			}
		}
	}

	public static LocalDate parseDate(String text) {

		List<DateTimeFormatter> knownPatterns = new ArrayList<DateTimeFormatter>();
		knownPatterns.add(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		knownPatterns.add(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		knownPatterns.add(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

		for (DateTimeFormatter pattern : knownPatterns) {
			// Take a try
			try {
				return LocalDate.parse(text, pattern);
			} catch (DateTimeParseException e) {
				// can't parse
			}
		}
		return null;
	}
}
