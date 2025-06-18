package com.basic.themePark;

public class DescriptionFormatter {
    public static String format(String raw) {
        if (raw == null || raw.isBlank()) return "";

        StringBuilder sb = new StringBuilder();
        String[] lines = raw.split("\\n");
        boolean insideList = false;

        for (String line : lines) {
            String trimmed = line.trim();

            if (trimmed.isEmpty()) continue;

            // Obsługa podpunktów zaczynających się od '*' lub '•'
            if (trimmed.startsWith("*") || trimmed.startsWith("•")) {
                if (!insideList) {
                    sb.append("<ul>\n");
                    insideList = true;
                }

                // usuń pierwszy znak i wszelkie dodatkowe spacje
                String item = trimmed.substring(1).stripLeading();

                sb.append("<li>").append(item).append("</li>\n");
            } else {
                if (insideList) {
                    sb.append("</ul>\n");
                    insideList = false;
                }

                sb.append("<p>").append(trimmed).append("</p>\n");
            }
        }

        if (insideList) {
            sb.append("</ul>\n");
        }

        return sb.toString();
    }
}

