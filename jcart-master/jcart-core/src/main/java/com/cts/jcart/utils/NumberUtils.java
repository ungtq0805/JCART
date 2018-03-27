/*
 * Copyright(C) 2008 D-CIRCLE, INC. All rights reserved.
 *
 * (1)このソフトウェアは、ディサークル株式会社に帰属する機密情報 であり開示を固く禁じます。
 * (2)この情報を使用するには、ディサークル株式会社とのライセンス 契約が必要となります。
 *
 * This software is the confidential and proprietary information of
 * D-CIRCLE, INC. You shall not disclose such Confidential Information
 * and shall use it only in accordance with the terms of the license
 * agreement you entered into with D-CIRCLE.
 */

package com.cts.jcart.utils;

import java.math.BigDecimal;
import java.util.regex.Pattern;


/**
 * 数値に関するユーティリティクラスです。
 * 
 * @author duy
 */
public class NumberUtils {

    public static final String NUM_CHAR = "^[0-9.,-]+";
    
	/**
     * 入力値が数かどうかチェックする
     * 
     * @param チェック値
     * @param マイナス可能フラグ
     * 
     * @return true : 数値
     * 		   false: 数値でない
     */
    public static boolean isNumber(String value, boolean isNegativePermit) {
        if (StringUtils.nullOrBlank(value)) {
            return true;
        }
        // 数値文字チェック
        if (!value.replaceAll("[.-]", "").equals(
                value.replaceAll("[^0-9]", ""))) {
            return false;
        }
        // マイナスチェック
        int firstMinus = value.indexOf("-");
        if (isNegativePermit) {
            if (firstMinus > -1) {
                if (firstMinus > 0) {
                    return false;
                }
                if (value.length() < 2) {
                    return false;
                }
                if (value.lastIndexOf("-") > 0) {
                    return false;
                }
            }
        } else {
            if (firstMinus > -1) {
                return false;
            }
        }
        // ドットチェック
        int firstDot = value.indexOf(".");
        if (firstDot > -1) {
            if (firstDot == 0) {
                return false;
            }
            if (firstDot == value.length() - 1) {
                return false;
            }
            if (firstDot != value.lastIndexOf(".")) {
                return false;
            }
        }
        // その他場合をチェック
        try {
            new BigDecimal(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    /**
     * カンマ編集を行います。<br>
     * また、ゼロサプレスや、小数点・マイナス符号などを考慮し、
     * 適切な数値表現にも編集します。
     * @return 編集後の文字列
     * @param aText 数値データ
     */
    public static String editCommaValue(String aText) {
        if (aText == null||aText.compareTo("")==0) {
            return "";
        }
        aText = aText.replace(",", "");
        boolean minusFlag = false;
        String sub = aText;
        // 最初の文字がマイナス符号の場合は、マイナス値とみなす
        if (aText.startsWith("-")) {
            minusFlag = true;
          //マイナス符号をはずして以降を処理
            sub = aText.substring(1);
        }
        //ゼロサプレス処理（但し、0の場合はサプレスしない）
        while (sub.startsWith("0") && sub.length() > 1) {
            sub = sub.substring(1);
        }
        //小数点がある場合は、小数点位置より左側（整数部）についてカンマ編集する
        if (sub.indexOf('.') != -1) {
            for (int i = sub.indexOf('.') - 3; i > 0; i--) {
                sub = sub.substring(0, i) + "," + sub.substring(i);
                i -= 2;
            }
        //小数点がない場合は、そのままカンマ編集する
        } else {
            for (int i = sub.length() - 3; i > 0; i--) {
                sub = sub.substring(0, i) + "," + sub.substring(i);
                i -= 2;
            }
        }
        //最後が小数点の場合は、これをカットする
        if (sub.endsWith(".")) {
            sub = sub.substring(0, sub.length() - 1);
        }
        //最初が小数点の場合は、先頭に"0"を付ける
        if (sub.startsWith(".")) {
            sub = "0" + sub;
        }
        //マイナス値の場合は、符号を付ける
        if (minusFlag) {
            sub = "-" + sub;
        }
        return sub;
    }
	
    /**
     * 数式の記号なのかチェック
     * 
     * @param チェック価値
     * @param パターン
     */
    public static boolean containsOnlyNumberChars(String value, String pattern) {
        if (StringUtils.nullOrBlank(pattern)) {
            pattern = NUM_CHAR;
        }
        return Pattern.matches(pattern, value);
    }
    
    //phapnv-Start
    public static Long toLong(Object value){
    	if(null==value){
    		return null;
    	}
    	try{
    		return Long.valueOf(value.toString());
    	}catch (Exception e) {
			return null;
		}
    }
    
    public static long toLongValue(Object value){
    	if(null==value){
    		return 0;
    	}
    	try{
    	    // PostgreDBの対応 : BigDecimal.toString : '3.00' → '3'
    	    if (isBigDecimal(value)) {
                return ((BigDecimal)value).longValue();
            }  
    		return Long.valueOf(value.toString());
    	}catch (Exception e) {
			return 0;
		}
    }
    
    public static Boolean toBoolean(Object value){
    	try{
    		return Boolean.valueOf(value.toString());
    	}catch (Exception e) {
			return null;
		}
    }
    //phapnv-End
    //phapnv-Start:#10906
    public static int toIntValue(Object value){
    	try{
    	    // PostgreDBの対応 : BigDecimal.toString : '3.00' → '3'
    	    if (isBigDecimal(value)) {
    	        return parseIntegerValue(value);
    	    } 
    		return Integer.valueOf(value.toString());
    	}catch (Exception e) {
			return 0;
		}
    }
    
    public static long parseLongValue(String number,int radix ){
    	try{
    		return Long.parseLong(number,radix);
    	}catch (Exception e) {
			return 0;
		}
    	
    }
  //phapnv-End:#10906
    //phapnn-Start:#10903
    public static Short toShort(String number){
    	try{
    		return Short.parseShort(number);
    	}catch(Exception ex){
    		return null;
    	}
    }
    
    public static Integer toInteger(String number){
    	try{
    		return Integer.parseInt(number);
    	}catch(Exception ex){
    		return null;
    	}
    }
    
    public static Integer toInteger(String number , int defaultValue){
    	Integer temp = toInteger(number);
    	if(temp == null){
    		return new Integer(defaultValue);
    	}
    	
    	return temp;
    }
    //phapnv-End:#10903
    
    /**
     * Convert x.000 to x value
     */
    public static Integer parseIntegerValue(Object obj) {
    	 if (isBigDecimal(obj)) {
    		 BigDecimal bigDec = (BigDecimal) obj ; 
    		 return new Integer(bigDec.intValue());
    	 }
    	 return new Integer(0);
    }
    
    private static boolean isBigDecimal(Object obj) {
        if (obj instanceof BigDecimal) {
            return true;
        }
        return false;
    }
    /**
     * 入力値が数かどうかチェックする
     * 
     * @param チェック値
     * @param Null値チェック : isNUll return false
     * @param マイナス可能フラグ
     * 
     * @return true : 数値
     * 		   false: 数値でない
     */
    public static boolean isNumber(String value,boolean checkValueNull, boolean isNegativePermit) {
		if (checkValueNull && StringUtils.nullOrBlank(value)) {
			return false;
		} else {
			return isNumber(value, isNegativePermit);
		}
    }
}