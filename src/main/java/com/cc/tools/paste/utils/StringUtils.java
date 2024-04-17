package com.cc.tools.paste.utils;

public class StringUtils {

    /**
     * 检查文本是否是下划线命名方式
     * @param text 待检查的文本
     * @return 如果是下划线命名方式返回true，否则返回false
     */
    public static boolean isSnakeCase(String text) {
        // 检查字符串是否为空或长度为0
        if (text == null || text.isEmpty()) {
            return false;
        }

        // 判断字符串是否以字母或下划线开头，并且只包含字母、数字和下划线
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (i == 0 && !Character.isLetter(ch) && ch != '_') {
                return false;
            }
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }

        return true;
    }

    /**
     * 将下划线的命名方法转换成驼峰命名方式
     * @param snakeCase 下划线命名方式
     * @return 返回转驼峰后的字符串
     */
    public static String snakeCaseToCamelCase(String snakeCase) {
        StringBuilder camelCase = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < snakeCase.length(); i++) {
            char currentChar = snakeCase.charAt(i);

            if (currentChar == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    camelCase.append(Character.toUpperCase(currentChar));
                    nextUpperCase = false;
                } else {
                    camelCase.append(Character.toLowerCase(currentChar));
                }
            }
        }

        return camelCase.toString();
    }
}
