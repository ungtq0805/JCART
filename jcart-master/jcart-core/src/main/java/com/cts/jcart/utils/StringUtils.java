/*
 * Copyright(C) 2007 D-CIRCLE, INC. All rights reserved.
 *
 * (1)このソフトウェアは、ディサークル株式会社に帰属する機密情報
 *    であり開示を固く禁じます。
 * (2)この情報を使用するには、ディサークル株式会社とのライセンス
 *    契約が必要となります。
 *
 * This software is the confidential and proprietary information
 * of D-CIRCLE, INC. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms
 * of the license agreement you entered into with D-CIRCLE.
 */

package com.cts.jcart.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ungtq
 */
public class StringUtils {

    private static final char[] ORIGINAL = { '&', '\"', '<', '>', '\''};
    private static final String[] CHANGE = { "&amp;", "&quot;", "&lt;", "&gt;", "&#39;" };
    private static final String ESC_CHAR = "\\";

    private static final String DEFAULT_DELIMITER = ",";

    // FEFF because this is the Unicode char represented by the UTF-8 byte order mark (EF BB BF).
    public static final String UTF8_BOM = "\uFEFF";

    /**
    * <code>\u000d</code> carriage return CR ('\r').
    *
    * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#101089">JLF: Escape Sequences
    *      for Character and String Literals</a>
    * @since 2.2
    */
    public static final char CR = '\r';
    /**
    * <code>\u000a</code> linefeed LF ('\n').
    *
    * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/lexical.html#101089">JLF: Escape Sequences
    *      for Character and String Literals</a>
    * @since 2.2
    */
    public static final char LF = '\n';
    /**
    * The empty String <code>""</code>.
    * @since 2.0
    */
    public static final String EMPTY = "";

    //phapnv-Modify
    public static final String BLANK = " ";
    //phapnv-End
    
//    private static final String UTF_8 =  "UTF-8"; 

    /**
     * nullまたは空文字、空白のみ（半角、全角）の場合はtrueを、それ以外はfalseを返す
     * @return
     */
    public static boolean isEmpty(Object obj) {
        if (nullOrBlank(obj)) {
            return true;
        } else {
            String s = obj.toString();
            s=s.replaceAll("[ 　]", "");
            return nullOrBlank(s);
        }
    }

    public static boolean nullOrBlank(Object obj) {
        if (obj == null) {
            return true;
        }
        if ("".equals(obj.toString())) {
            return true;
        }
        return false;
    }
    /**
     * 文字列チェック処理。指定の文字列が空文字でないことを判定する。
     * @param target チェックする文字列の指定
     * @return boolean 判定結果。true：空文字ではない、false：nullまたは空文字
     */
    public static synchronized boolean isValidString(String target) {
    	return (target != null && target.length() > 0);
    }
    public static String addEmpty(Object obj) {
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
    /**
    * 金額を３桁毎のカンマ区切にする
    * 作成者 : (M.Maemura)
    * @return java.lang.String
    */
    public static String formatNumber(Long number) {
	String result = "";
	String st = number.toString();
	int j = 0;
	for (int i = st.length() - 1; i > 0; i--){
            result = st.charAt(i) + result;
            j++;
            if (j == 3) {
                j = 0;
                result = "," + result;
            }
	}
	result = st.charAt(0) + result;
	return result;
    }
    /**
     * 日付文字列をYYYY/MM/DDに編集する
     * 作成日 : (01/08/25 22:31:06)
     * 作成者 : (M.Maemura)
     * @return java.lang.String
     */
    public static String formatDate(Date aDate) {
        String result = "";
        if (aDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(aDate);
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DATE));
            if (Integer.valueOf(month).intValue() < 10) {
                month = "0" + month;
            }
            if (Integer.valueOf(day).intValue() < 10) {
                day = "0" + day;
            }
            result = year + "/" + month + "/" + day;
        }
        return result;
    }

    public static String formatTime(String time) {
    	if (nullOrBlank(time)) {
    		return "";
    	}
    	if (time.length() != 4) {
    		return time;
    	}
    	return time.substring(0,2) + ":" + time.substring(2,4);
    }

    public static String toEmpty(Object obj) {
    	if (obj == null) {
    		return "";
    	}
    	return obj.toString();
    }

    /**
     * 日付文字列を "01月23日"に編集する
     * 作成日 : 2008/03/22
     * 作成者 : (thaobt)
     * @return java.lang.String
     */
    public static String formatDateSubject(Date aDate) {
        String result = "";
        if (aDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(aDate);
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DATE));
            if (Integer.valueOf(month).intValue() < 10) {
                month = "0" + month;
            }
            if (Integer.valueOf(day).intValue() < 10) {
                day = "0" + day;
            }
            result =month + "月" + day + "日";
        }
        return result;
    }

    /**
     * 日付文字列をYYYY/MMに編集する
     * 作成日 : 2008.03.27
     * 作成者 : Trangnttn
     * @return java.lang.String
     */
    public static String formatYearMonth(Date aDate) {
        String result = "";
        if (aDate != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(aDate);
            String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            if (Integer.valueOf(month).intValue() < 10) {
                month = "0" + month;
            }
            result = year + "/" + month ;
        }
        return result;
    }
    /**
     * 先頭にOを追加する
     * @param value
     * @param num
     * @return
     */
    public static String addZero(Object value, int num) {
    	if (nullOrBlank(value)) {
    		return "";
    	}
    	String ret = value.toString();
    	for (int i=ret.length(); i<num;i++) {
    		ret = "0" + ret;
    	}
    	return ret;
    }
    /**
     * オブジェクトを指定されたフォーマットで数値フォーマットされた文字列に変換します。<BR>
     * @return 変換された文字列。変換前にnullが渡された場合は空文字、不正な引数の場合は変換前の文字列を返す。
     * @param aOriginal 変換前オブジェクト。toString()により数値化可能な文字列化が可能であること。
     * @param aFormat 編集フォーマット文字列（NumberFormatクラスに準拠）
     */
    public static String formatNumber(Object aOriginal, String aFormat) {
        String originalStr = new String();
        try {
            originalStr = aOriginal.toString();
            /**
             * AIT-Longgt modified
             * converting originalStr from Double to BigDecimal
             * because of error described in ticket #9559
             */
            return (new java.text.DecimalFormat(aFormat)).format(
                new BigDecimal(originalStr));
        } catch (NullPointerException npe) {
            return new String();
        } catch (NumberFormatException nfe) {
            return originalStr;
        } catch (Exception e) {
            return originalStr;
        }
    }

    /**
    * Cp943C文字コード調整
    *
    * @param オリジナル文字列
    * @return Cp943C変換後文字列
    */
    public static String changeToCp943C(String  original) {
        // 2003.01.08 K.Kawano
        // 実際の変換処理を1文字単位の処理として分離
        // null対応

        try {
            char[] tArray = original.toCharArray();
            int arrLength = tArray.length;
            for(int cnt = 0; arrLength > cnt; cnt++) {
                tArray[cnt] = changeToCp943C(tArray[cnt]);
            }
            return new String(tArray);
        } catch (NullPointerException e) {
             return "";
        }
    }

    /**
    * Cp943C文字コード調整
    *
    * @param オリジナル文字
    * @return Cp943C変換後文字
    */
    public static char changeToCp943C(char original) {
        switch( (int)original ) {
            //@ case 0xffe3:    original = 0xffe3;  break;  //￣ OverLine
            case 0x2015:    original = 0x2014;  break;  //― EM Dash
            //@ case 0x005c:    original = 0xff3c;  break;  //＼ ReverseSolidus
            case 0xff5e:    original = 0x301c;  break;  //～ WAVE DASH
            case 0x2225:    original = 0x2016;  break;  //∥ DoubleVerticalLine
            case 0x22ef:    original = 0x2026;  break;  //… HolizontalEllipsis
            case 0xff0d:    original = 0x2212;  break;  //－ Minus Sign
        //    case 8722:    original = 0x2212;  break;  //－ Minus Sign
            //@ case 0xffe5:    original = 0x00a5;  break;  //￥ Yen Sign
            case 0x00a2:    original = 0xffe0;  break;  //￠ Cent Sign
            case 0x00a3:    original = 0xffe1;  break;  //￡ Pound Sign
            case 0x00ac:    original = 0xffe2;  break;  //￢ Not Sign
        }
        return  original;
    }

    public static String convertToWindows31J(String original){
    	try {
            char[] tArray = original.toCharArray();
            int arrLength = tArray.length;
            for(int cnt = 0; arrLength > cnt; cnt++) {
                tArray[cnt] = changeToWindows31J(tArray[cnt]);
            }
            return new String(tArray);
        } catch (NullPointerException e) {
             return "";
        }
    }

    public static char changeToWindows31J(char original) {

    	switch( (int)original ) {

	    	  //@ case 0xffe3:    original = 0xffe3;  break;  //￣ OverLine
	          case 0x2015:    original = 0x2015;  break;  //― EM Dash
	          //@ case 0x005c:    original = 0xff3c;  break;  //＼ ReverseSolidus
	          case 0xff5e:    original = 0xff5e;  break;  //～ WAVE DASH
	          case 0x2225:    original = 0x2225;  break;  //∥ DoubleVerticalLine
	          case 0x22ef:    original = 0x2026;  break;  //… HolizontalEllipsis
	          case 0xff0d:    original = 0xff0d;  break;  //－ Minus Sign
	          case 0x2212:  original = 0xff0d;  break;  //－ Minus Sign

	          //@ case 0xffe5:    original = 0x00a5;  break;  //￥ Yen Sign
	          case 0x00a2:    original = 0xffe0;  break;  //￠ Cent Sign
	          case 0x00a3:    original = 0xffe1;  break;  //￡ Pound Sign
	          case 0x00ac:    original = 0xffe2;  break;  //￢ Not Sign
    	}
      return  original;
    }
    /**
    * Get number of byte of param string.
    * @param value
    * @return int value
    * @create 2008/06/24
    * @author tranghv
    */
    public static int getByteCount(String value){
        if(nullOrBlank(value)){
            return 0;
        }
        int byteCount = 0;

        for (int i = 0; i < value.length(); i++) {

            String charAt = value.substring(i,i+1);
            try{
                byteCount += charAt.getBytes("SJIS").length;
            }catch(UnsupportedEncodingException e){
            }
        }

        return byteCount;
    }
    public static int getByteCountUTF8(String value){
        if(nullOrBlank(value)){
            return 0;
        }
        int byteCount = 0;

        for (int i = 0; i < value.length(); i++) {

            String charAt = value.substring(i,i+1);
            try{
                byteCount += charAt.getBytes("UTF-8").length;
            }catch(UnsupportedEncodingException e){
            }
        }

        return byteCount;
    }
//    public static String changeEncoding(String orginal, String encoding) {
//        if (orginal == null) {
//            return null;
//        }
//        try {
//            StringBuffer retStr = new StringBuffer();
//            URLCodec encoder = new URLCodec(encoding);
//            for (int i=0; i< orginal.length(); i++) {
//                String s = encoder.encode(orginal.substring(i,i+1));
//                retStr.append(s);
//            }
//            return retStr.toString();
//        } catch (EncoderException e) {
//            throw new RuntimeException(e);
//        }
//    }
    /**
     *
     * @param orginal
     * @return
     */
    public static String changeToUTF8(String orginal) {
        if (orginal == null) {
            return "";
        }
        try {
            byte[] bytes = orginal.getBytes("SJIS");
            return new String(bytes,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long convertToLong(String obj) {
        if (StringUtils.nullOrBlank(obj)) {
            return new Long(0);
        }
        try {
            return Long.valueOf(obj);
        } catch (Exception e) {
            return new Long(0);
        }
    }

      /**
       * 文字列の分割
       * 文字列を指定した区切り文字で分割する
       *
       * @param   aStr        文字列
       * @param   aSeprator   区切り文字
       * @return  分割した文字列のリスト
       */
      public static ArrayList<String> separateValueString(String aStr, String  aSeprator ) {
          String w;
          int ipos;
          ArrayList<String> list = new ArrayList<String>();

          //  while( (ipos = aStr.indexOf(aSeprator)) !=-1)
          while(true)  {
              //区切り文字を探す
              int     isp=0;
              while(true) {
                  int ifnd;
                  ifnd = aStr.indexOf(aSeprator, isp);
                  if (ifnd<0) {    //なければ
                      ipos = ifnd;
                      break;
                  } else {
                      if (ifnd != 0) {       //先頭からじゃなしまで
                          w = aStr.substring(ifnd-1,ifnd);    //直前
                          if(w.compareTo(ESC_CHAR)==0) {
                              aStr = aStr.substring(0,ifnd-1) + aSeprator
                                   + aStr.substring(ifnd+1);
                              isp  = ifnd;
                          } else {
                              ipos = ifnd;
                              break;
                          }
                      }else{
                          ipos = ifnd;
                          break;
                      }
                  }
              }
              if(ipos<0) {
                  break;
              }
              //-----------------------------------------
              if(ipos!=0) {   //先頭からセパレータまで
                  w = aStr.substring(0,ipos);
              } else {          //先頭がセパレータ
                  w = "";
              }
              if ( aStr.length()>ipos+1 ) {
                  aStr = aStr.substring( ipos + 1 );
              } else {
                  aStr = "";
              }
              list.add( w );
          }
          //最後の残り
          list.add( aStr );
          return list;
      }
      
//      synchronized static public String formUrl(String baseUrl) {
//          if (baseUrl.startsWith("/")) {
//              return baseUrl;
//          } else if (baseUrl.startsWith("http://") || baseUrl.startsWith("https://")) {
//              return baseUrl;
//          } else {
//              return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/" + baseUrl;
//          }
//      }

      /**
       * CSV行テキストを受け取り、項目毎に分割した配列を返します.<BR>
       * 項目の括り（" or '）は除去されます.<BR>
       * 受け取ったテキストがnullの場合は、size=0の配列を返します.<BR>
       * 受け取ったテキストが""の場合は、先頭要素""の配列を返します.<BR>
       * @return データ配列（要素：String型）
       * @param csvLine 処理対象のテキスト
       * @param delimiter 区切り文字
       */
      static public String[] splitCsvValueWithQuot(String csvLine, String delimiter) {
          return splitCsvValue(csvLine, delimiter, true);
      }
      /**
       * CSV行テキストを受け取り、項目毎に分割した配列を返します.<BR>
       * 項目の括り（" or '）は除去されます.<BR>
       * 受け取ったテキストがnullの場合は、size=0の配列を返します.<BR>
       * 受け取ったテキストが""の場合は、先頭要素""の配列を返します.<BR>
       * @return データ配列（要素：String型）
       * @param csvLine 処理対象のテキスト
       */
      static public String[] splitCsvValueWithQuot(String csvLine) {
          return splitCsvValue(csvLine, DEFAULT_DELIMITER, true);
      }
      /**
       * CSV行テキストを受け取り、項目毎に分割した配列を返します.<BR>
       * 項目の括り（" or '）は除去されません.<BR>
       * 受け取ったテキストがnullの場合は、size=0の配列を返します.<BR>
       * 受け取ったテキストが""の場合は、先頭要素""の配列を返します.<BR>
       * @return データ配列（要素：String型）
       * @param csvLine 処理対象のテキスト
       * @param delimiter 区切り文字
       */
      static public String[] splitCsvValueNoQuot(String csvLine, String delimiter) {
          return splitCsvValue(csvLine, delimiter, false);
      }
      /**
       * CSV行テキストを受け取り、項目毎に分割した配列を返します.<BR>
       * 項目の括り（" or '）は除去されません.<BR>
       * 受け取ったテキストがnullの場合は、size=0の配列を返します.<BR>
       * 受け取ったテキストが""の場合は、先頭要素""の配列を返します.<BR>
       * @return データ配列（要素：String型）
       * @param csvLine 処理対象のテキスト
       */
      static public String[] splitCsvValueNoQuot(String csvLine) {
          return splitCsvValue(csvLine, DEFAULT_DELIMITER, false);
      }

      /**
       * CSV行テキストを受け取り、項目毎に分割した配列を返します。<BR>
       * aQuotationFlagがtrueの場合、配列にセットした各データの先頭と最後の文字が
       * どちらも" 又は どちらも' の場合、それを除去します。<BR>
       * 受け取ったテキストがnullの場合は、要素無しのArrayListを戻り値とします。
       * 受け取ったテキストが""の場合は、先頭要素""のArrayListを戻り値とします。<BR>
       * @return データ配列（要素：String型）
       * @param csvLine 処理対象のテキスト
       * @param delimiter 区切り文字
       * @param hasQuot 文字データが"や'でくくられているかどうかのフラグ（true:くくられている  false:くくられていない）
       */
      private static String[] splitCsvValue(
              String csvLine, String delimiter, boolean hasQuot) {
          String restSt = csvLine;
          String element =""; //取り出す要素
          List<String> valueList = new ArrayList<String>();
          int fNo = -1;

          // パラメタのテキストがnullの場合は、要素無しのArrayListで返します。
          if (csvLine == null) {
              return new String[0];
          }

          do {
              boolean endFlg = false;
              if(restSt.equals("")) {   //文字列が空のとき
                  element = "";
                  valueList.add(element);
                  fNo = -1;
              } else {
                  // Quoattionあり（除去する）場合
                  if(hasQuot) {
                      String sCh = restSt.substring(0,1);
                      if(sCh.compareTo("'") == 0 || sCh.compareTo("\"") == 0) { //クォーテーション始まり
                          //-- [1番目のQuoattion]～[2番目のQuoattionの次のDelimiter]を一つの項目とする
                          //-- 1番目と2番目のQuoattionを除去する
                          //-- 2番目のQuoattionからDelimiterまでの文字列をデータに含める
                          //2番目のQuoattionを探す
                          int eChNo = restSt.indexOf(sCh, 1);
                          if (eChNo != -1) {
                              //Quoattion内の文字列を取り出す
                              element = restSt.substring(1, eChNo);
                              //その次のDelimiterを探す
                              int sepNo = restSt.indexOf(delimiter, eChNo + 1);
                              if (sepNo != -1) {
                                  //Delimiterまでの文字列を付け足す
                                  element += restSt.substring(eChNo + 1, sepNo);
                                  //データ配列に追加
                                  valueList.add(element);
                                  //次項目あり
                                  restSt = restSt.substring(sepNo + 1);
                                  fNo = 0;
                                  endFlg = true;
                              } else {
                                  //Delimiterがない場合、最後までの文字列を付け足す
                                  element += restSt.substring(eChNo + 1);
                                  //データ配列に追加
                                  valueList.add(element);
                                  //項目終了
                                  restSt = "";
                                  fNo = -1;
                                  endFlg = true;
                              }
                          }
                      }
                  }
                  if(! endFlg) {
                      fNo = restSt.indexOf(delimiter); // 最初のdelimiter の位置を取得、delimiterがないときは-1を返す。
                      if(fNo != 0) {          //delimiter が1文字目ではないとき
                          if(fNo != -1) {     //delimiter があったとき
                              element = restSt.substring(0, fNo);
                              if(hasQuot)
                                  element = removeQuotation(element);
                              valueList.add(element);
                              restSt = restSt.substring(fNo + 1);
                          } else {    //もう fieldDelimiter がない場合
                              element = restSt;
                              if(hasQuot)
                                  element = removeQuotation(element);
                              valueList.add(element);
                          }
                      } else {        //文字列の最初の文字が delimiter
                          element ="";
                          valueList.add(element);
                          restSt = restSt.substring(1);
                      }
                  }
              }
          } while(fNo != -1);

          return (String[])valueList.toArray(new String[valueList.size()]);
      }

      /**
       * 先頭と最後の文字がどちらも" または どちらも'の場合、それを除去します.<BR>
       * @return 除去後の文字列
       * @param element 処理対象の文字列
       */
      private static String removeQuotation(String element) {
          if(element.length() == 1)
              return element;
          String headChar = element.substring(0,1);
          String endChar = element.substring(element.length() - 1, element.length());
          if(headChar.equals("'")  || headChar.equals("\"")) {
              if(headChar.equals(endChar)) {  //先頭と最後が同じ場合
                  element = element.substring(1, element.length()-1);
              }
          }
          return element;
      }

      /**
       * HTML用文字列エンコード処理
       *　& -> &amp;
       *  \ -> &quot;
       *  < -> &lt;
       *  > -> &gt;
       *  ' -> &#39;
       *
       * @param オリジナル文字列
       * @return エンコード後文字列
       */
      public static String parseHtmlString(String original) {

          // 2003.01.08 K.Kawano
          // Stringの結合をStringBufferに変更
          // ループ内でのインスタンス生成が発生しないよう修正
          // Cp943Cへの変換をループ内で1文字単位で行うよう最適化
          // ループの終了判定のために毎回lengthプロパティを参照するのを廃止し、変数化
          try {

              if(StringUtils.nullOrBlank(original)){
                  return "";
              }

              StringBuffer result = new StringBuffer(original.length());

              char[] cArray = original.toCharArray();
              int arrLength = cArray.length;
              char workChar;
              int orgLength = ORIGINAL.length;

              for (int cnt = 0; arrLength > cnt; cnt++) {
                  workChar = cArray[cnt];
//                  workChar = changeToCp943C(workChar);
                  boolean found = false;
                  for (int cnt1 = 0; orgLength > cnt1; cnt1++) {
                      if (workChar == ORIGINAL[cnt1]) {
                          result.append(CHANGE[cnt1]);
                          found = true;
                          break;
                      }
                  }
                  if (!found) {
                      result.append(workChar);
                  }
              }
              return result.toString();

          } catch (NullPointerException e) {
              return "";
          }
      }

      /**
       * ASCIIコードでの制御文字(0x00~0x1f,0x7f)を除去する.
       * 但し、タブと改行の制御文字はそのまま残す。
       * @param inText
       * @return String
       */
      public static String removeControlChar(String inText){
          if (inText == null) {
              return null;
          }
          //ASCIIコードでの制御文字(0x00~0x1f,0x7f)からタブ(0x09)と改行(0x0a,0x0d)を除く文字を検索する正規表現
          String regExpStr = "[\\00-\\x08\\x0b-\\x0c\\x0e-\\x1f\\x7f]";
          return inText.replaceAll(regExpStr, "");
      }

      /**
       * 空白を解除する。
       * @param inText
       * @return
       * @author trangnttn
       * @create 2009/10/02
       */
      public static String trimBlank(String inText){
          if(StringUtils.nullOrBlank(inText)){
              return "";
          }
          return inText.trim();
      }

      /**
       * テスキトはnull・空白・スペースかどうかチェック
       * @param チェックするテキスト
       * @author duy
       */
      public static boolean nullBlankSpace(String text) {
    	  if (text == null) {
    		  return true;
    	  }
    	  return nullOrBlank(text.replaceAll(" ", "").replaceAll("　", ""));
      }

      /**
       * Replaces each substring of this string that matches the given regular expression with the given replacement.
       * An invocation of this method of the form str.replaceAll(regex, repl) yields exactly the same result as the expression
       * @param value
       * @return
       */
      public static String replaceAll(String value , String regex, String replacement){

          value = toEmpty(value);
          regex = toEmpty(regex);
          replacement = toEmpty(replacement);

          return value.replaceAll(regex, replacement).trim();
      }

	/**
	 * 全角ひらがなを全角カタカナへ変換する
	 *
	 * @param hiragana
	 * @return String
	 */
	public static String convertHiragana2Katakana(String hiragana) {
		StringBuffer katakana = new StringBuffer(hiragana);
		for (int i = 0; i < katakana.length(); i++) {
			char c = katakana.charAt(i);
			if (c >= 'ぁ' && c <= 'ん') {
				katakana.setCharAt(i, (char) (c - 'ぁ' + 'ァ'));
			}
		}
		return katakana.toString();
	}

	/**
	 * Process for Assist Message
	 * //In case message content is : <font style="text-color:red">Sample7>&;></font>
	 * @param message
	 * @return
	 */
//	public static String parseXMLString(String oldMessage){
//
//	    String textNode = "";
//        String oldTextNode="";
//        oldMessage = StringUtils.toEmpty(oldMessage);
//
//        //In case message content is : <font style="text-color:red">Sample7>&;></font>
//        DOMParser parser = new DOMParser();
//        try {
//            parser.parse(new InputSource(new StringReader(oldMessage)));
//            textNode = getTextNodes(parser.getDocument(), oldMessage);
//            oldTextNode = new String(textNode);
//            textNode = StringUtils.parseHtmlString(textNode);
//        } catch (Exception e) {
//            return oldTextNode;//reset old value
//        }
//
//        textNode =  oldMessage.replace(oldTextNode, textNode);
//
//        return textNode;
//
//	}

//    private static boolean isNewLineNode(Node node) {
//        if (node != null
//                && (node.getNodeName().equalsIgnoreCase("br"))) {
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean isBlockNode(Node node) {
//        Node targetNode = node.getPreviousSibling() != null ? node.getPreviousSibling() : node.getParentNode();
//        if (targetNode != null
//                && (targetNode.getNodeName().equalsIgnoreCase("div")
//                        || targetNode.getNodeName().equalsIgnoreCase("dd")
//                        || targetNode.getNodeName().equalsIgnoreCase("dt")
//                        || targetNode.getNodeName().equalsIgnoreCase("h1")
//                        || targetNode.getNodeName().equalsIgnoreCase("h2")
//                        || targetNode.getNodeName().equalsIgnoreCase("h3")
//                        || targetNode.getNodeName().equalsIgnoreCase("h4")
//                        || targetNode.getNodeName().equalsIgnoreCase("h5")
//                        || targetNode.getNodeName().equalsIgnoreCase("h6")
//                        || targetNode.getNodeName().equalsIgnoreCase("li")
//                        || targetNode.getNodeName().equalsIgnoreCase("p")
//                        || targetNode.getNodeName().equalsIgnoreCase("td")
//                        || targetNode.getNodeName().equalsIgnoreCase("th"))) {
//            return true;
//        }
//        return false;
//    }
//    private static boolean isIgnoreNode(Node node) {
//        if (node.getNodeName().equalsIgnoreCase("style")
//                || node.getNodeName().equalsIgnoreCase("script")) {
//            return true;
//        }
//        return false;
//    }

//    private static String getTextNodes(Node node, String message) {
//        StringBuffer resultString = new StringBuffer();
//        for (
//                Node child = node.getFirstChild();
//                child != null;
//                child = child.getNextSibling()) {
//            if (child.getNodeType() == Node.TEXT_NODE) {
//                if (isBlockNode(child)
//                        || isNewLineNode(child)) {
//                    resultString.append("\r\n");
//                }
//                if (child.getNodeValue().equals("&nbsp;")
//                        || child.getNodeValue().equals(" ")
//                        || child.getNodeValue().equals("\t")
//                        || child.getNodeValue().equals("\n")
//                        || child.getNodeValue().getBytes().equals(0xa0)
//                        || child.getNodeValue().getBytes().equals(0x3f)
//                        || (child.getNodeValue().getBytes().length == 1 && child.getNodeValue().getBytes()[0] == 63)
//                        ) {
//                } else {
//                    resultString.append(child.getNodeValue());
//                }
//            } else if (!isIgnoreNode(child)) {
//                resultString.append(getTextNodes(child, message));
//            }
//        }
//        return resultString.toString();
//    }

    public static final String escapeHTML(String s){
        StringBuffer sb = new StringBuffer();
        int n = s.length();
        for (int i = 0; i < n; i++) {
           char c = s.charAt(i);
           switch (c) {
              case '<': sb.append("&lt;"); break;
              case '>': sb.append("&gt;"); break;
              case '&': sb.append("&amp;"); break;
              case '"': sb.append("&quot;"); break;
              case 'à': sb.append("&agrave;");break;
              case 'À': sb.append("&Agrave;");break;
              case 'â': sb.append("&acirc;");break;
              case 'Â': sb.append("&Acirc;");break;
              case 'ä': sb.append("&auml;");break;
              case 'Ä': sb.append("&Auml;");break;
              case 'å': sb.append("&aring;");break;
              case 'Å': sb.append("&Aring;");break;
              case 'æ': sb.append("&aelig;");break;
              case 'Æ': sb.append("&AElig;");break;
              case 'ç': sb.append("&ccedil;");break;
              case 'Ç': sb.append("&Ccedil;");break;
              case 'é': sb.append("&eacute;");break;
              case 'É': sb.append("&Eacute;");break;
              case 'è': sb.append("&egrave;");break;
              case 'È': sb.append("&Egrave;");break;
              case 'ê': sb.append("&ecirc;");break;
              case 'Ê': sb.append("&Ecirc;");break;
              case 'ë': sb.append("&euml;");break;
              case 'Ë': sb.append("&Euml;");break;
              case 'ï': sb.append("&iuml;");break;
              case 'Ï': sb.append("&Iuml;");break;
              case 'ô': sb.append("&ocirc;");break;
              case 'Ô': sb.append("&Ocirc;");break;
              case 'ö': sb.append("&ouml;");break;
              case 'Ö': sb.append("&Ouml;");break;
              case 'ø': sb.append("&oslash;");break;
              case 'Ø': sb.append("&Oslash;");break;
              case 'ß': sb.append("&szlig;");break;
              case 'ù': sb.append("&ugrave;");break;
              case 'Ù': sb.append("&Ugrave;");break;
              case 'û': sb.append("&ucirc;");break;
              case 'Û': sb.append("&Ucirc;");break;
              case 'ü': sb.append("&uuml;");break;
              case 'Ü': sb.append("&Uuml;");break;
              case '®': sb.append("&reg;");break;
              case '©': sb.append("&copy;");break;
              case '€': sb.append("&euro;"); break;
              // be carefull with this one (non-breaking white space)
              //case ' ': sb.append("&nbsp;");break;

              default:  sb.append(c); break;
           }
        }
        return sb.toString();
     }
    /**
     * target文字列を指定したlengthのバイト数以下の文字列に切り取る
     * @param target
     * @param length
     * @return
     */
	public static String truncate(String target, int length) {
		CharsetEncoder encoder = Charset.forName("utf-8").newEncoder();
		if (length >= encoder.maxBytesPerChar() * target.length()) {
			return target;
		}
		CharBuffer buffer = CharBuffer.wrap(new char[Math.min(target.length(), length)]);
		target.getChars(0, Math.min(target.length(), buffer.length()), buffer.array(), 0);
		if (length >= encoder.maxBytesPerChar() * buffer.limit()) {
			return buffer.toString();
		}
		ByteBuffer byteBuffer = ByteBuffer.allocate(length);
		encoder.reset();
		CoderResult result = buffer.hasRemaining()
				? encoder.encode(buffer, byteBuffer, true)
				: CoderResult.UNDERFLOW;
		if (result.isUnderflow()) {
			result = encoder.flush(byteBuffer);
		}
		return buffer.flip().toString();
	}

	/**
     * This method will convert a collection to string
     * Ex: s = {a, b, c} . delimiter = ";"
     * Result : a;b;c
     * @param s
     * @param delimiter
     * @return
     */
    public static String join(Collection<String> s, String delimiter) {

        if(s == null || s.size() ==0){
            return "";
        }

        StringBuffer buffer = new StringBuffer();
        Iterator<String> iter = s.iterator();

        while (iter.hasNext()) {
            buffer.append(iter.next());
            if (iter.hasNext()) {
                buffer.append(delimiter);
            }
        }
        return buffer.toString();
    }

    public static String join(String[] array , String separator){

        if(array== null || array.length == 0){
            return "";
        }

        StringBuffer result = new StringBuffer();
        result.append(array[0]);

        for (int i=1; i<array.length; i++) {
           result.append(separator);
           result.append(array[i]);
        }
        return result.toString();
    }

    // Chomping
    //-----------------------------------------------------------------------
    /**
     * <p>Removes one newline from end of a String if it's there,
     * otherwise leave it alone.  A newline is &quot;<code>\n</code>&quot;,
     * &quot;<code>\r</code>&quot;, or &quot;<code>\r\n</code>&quot;.</p>
     *
     * <p>NOTE: This method changed in 2.0.
     * It now more closely matches Perl chomp.</p>
     * <pre>
     * StringUtils.chomp(null)          = null
     * StringUtils.chomp("")            = ""
     * StringUtils.chomp("abc \r")      = "abc "
     * StringUtils.chomp("abc\n")       = "abc"
     * StringUtils.chomp("abc\r\n")     = "abc"
     * StringUtils.chomp("abc\r\n\r\n") = "abc\r\n"
     * StringUtils.chomp("abc\n\r")     = "abc\n"
     * StringUtils.chomp("abc\n\rabc")  = "abc\n\rabc"
     * StringUtils.chomp("\r")          = ""
     * StringUtils.chomp("\n")          = ""
     * StringUtils.chomp("\r\n")        = ""
     * </pre>
     *
     * @param str  the String to chomp a newline from, may be null
     * @return String without newline, <code>null</code> if null String input
     */
    public static String chomp(String str) {
        if (isEmpty(str)) {
            return str;
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == CR || ch == LF) {
                return EMPTY;
            }
            return str;
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == LF) {
            if (str.charAt(lastIdx - 1) == CR) {
                lastIdx--;
            }
        } else if (last != CR) {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    /**
     * This method will convert values to [[values...],[values...]]
     * Ex : List values = { obj1 = [a1,...,an], obj2 = [b1,...,bn],.....,objn = [x1,x2,...,xn]}
     *   The return value : [[a1,...,an],[b1,...,bn],[x1,x2,...,xn]]
     *   In case of values == null or empty. The return value will be [[]]
     * @param values
     * @return  [[a1,...,an],[b1,...,bn],[x1,x2,...,xn]] or [[]]
     */
    public static String serialize(Collection<List<String>> values){

        if(values == null || values.size() == 0){
            return "[[]]";
        }

        StringBuffer buff = new StringBuffer();
        buff.append("[");
        Iterator<List<String>> iter = values.iterator();

        int count = 0;

        while ( iter.hasNext() ){
            if(count>0){
                buff.append(",");
            }
            buff.append(serialize(iter.next()));
            count++;
        }
        buff.append("]");

        return buff.toString();
    }

    /**
     * This method will convert List<String> params to String
     * Example :
     *     List<String> params = { a, b, c};
     *  If params == null or params.size() ==0 . It will return "[]"
     * The return value of method : [a,b,c]
     * @param params
     * @return [a,b,c] or []
     */
    public static String serialize(List<String> params){
        return "["+ join(params, ",")+"]";
    }

    /**
     * remove char 'BOM' in param s
     * @param s
     * @return
     */
    public static String removeUTF8BOM(String s) {
        if (s.startsWith(UTF8_BOM)) {
            s = s.substring(1);
        }
        return s;
    }

    /**
     * <p>Gets the substring after the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the empty string if the
     * input string is not <code>null</code>.</p>
     *
     * <pre>
     * StringUtils.substringAfter(null, *)      = null
     * StringUtils.substringAfter("", *)        = ""
     * StringUtils.substringAfter(*, null)      = ""
     * StringUtils.substringAfter("abc", "a")   = "bc"
     * StringUtils.substringAfter("abcba", "b") = "cba"
     * StringUtils.substringAfter("abc", "c")   = ""
     * StringUtils.substringAfter("abc", "d")   = ""
     * StringUtils.substringAfter("abc", "")    = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring after the first occurrence of the separator,
     *  <code>null</code> if null String input
     */
    public static String substringAfter(String str, String separator) {
        if (nullOrBlank(str) || nullOrBlank(separator)) {
            return str;
        }

        int pos = str.indexOf(separator);
        if (pos == -1) {
            return "";
        }
        return str.substring(pos + separator.length());
    }

    /**
     * <p>Gets the substring before the last occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * An empty or <code>null</code> separator will return the input string.</p>
     *
     * <pre>
     * StringUtils.substringBeforeLast(null, *)      = null
     * StringUtils.substringBeforeLast("", *)        = ""
     * StringUtils.substringBeforeLast("abcba", "b") = "abc"
     * StringUtils.substringBeforeLast("abc", "c")   = "ab"
     * StringUtils.substringBeforeLast("a", "a")     = ""
     * StringUtils.substringBeforeLast("a", "z")     = "a"
     * StringUtils.substringBeforeLast("a", null)    = "a"
     * StringUtils.substringBeforeLast("a", "")      = "a"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring before the last occurrence of the separator,
     *  <code>null</code> if null String input
     */
    public static String substringBeforeLast(String str, String separator) {
        if (nullOrBlank(str) || nullOrBlank(separator)) {
            return str;
        }
        int pos = str.lastIndexOf(separator);
        if (pos == -1) {
            return str;
        }
        return str.substring(0, pos);
    }


    /**
     * <p>Gets the substring before the first occurrence of a separator.
     * The separator is not returned.</p>
     *
     * <p>A <code>null</code> string input will return <code>null</code>.
     * An empty ("") string input will return the empty string.
     * A <code>null</code> separator will return the input string.</p>
     *
     * <pre>
     * StringUtils.substringBefore(null, *)      = null
     * StringUtils.substringBefore("", *)        = ""
     * StringUtils.substringBefore("abc", "a")   = ""
     * StringUtils.substringBefore("abcba", "b") = "a"
     * StringUtils.substringBefore("abc", "c")   = "ab"
     * StringUtils.substringBefore("abc", "d")   = "abc"
     * StringUtils.substringBefore("abc", "")    = ""
     * StringUtils.substringBefore("abc", null)  = "abc"
     * </pre>
     *
     * @param str  the String to get a substring from, may be null
     * @param separator  the String to search for, may be null
     * @return the substring before the first occurrence of the separator,
     *  <code>null</code> if null String input
     */
    public static String substringBefore(String str, String separator) {

        if (nullOrBlank(str) || nullOrBlank(separator)) {
            return str;
        }

        int pos = str.indexOf(separator);

        if (pos == -1) {
            return str;
        }

        return str.substring(0, pos);
    }
    /**
     * Get bytes with encode
     * @param value
     * @param encode
     * @return
     */
    public static int countByte(String value, String encode) {
    	if (nullOrBlank(value)) {
    		return 0;
    	}
    	int cnt = 0;
        try {
            cnt = value.getBytes(encode).length;
        } catch (UnsupportedEncodingException ex) {
        }
        return cnt;
    }

    //phapnv-Start:#12660
    public static boolean isMailAddress(String maiAddress){
        String literalMailAddress = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-_@!#$%&*+/=?`{|}";
        boolean validate = false;
        if("".equals(maiAddress) || maiAddress==null){
            return true;
        }
        int count=0;
        for(int idex = 0;idex<maiAddress.length()-2;idex++){
            if("@".equals(maiAddress.substring(idex,idex+1))){
                count++;
            }
        }
        if(count!=1){
            return false;
        }
        int index;
        index = maiAddress.indexOf(".");
        if(index<1 || index >= maiAddress.length()-1){
            return false;
        }
        index = maiAddress.indexOf("_");
        if(index>=0 && (index<1 || index >= maiAddress.length()-1)){
            return false;
        }
        index = maiAddress.indexOf("-");
        if(index>=0 && (index<1 || index >= maiAddress.length()-1)){
            return false;
        }
        for(int idex=0;idex<maiAddress.length();idex++){
            String literal = maiAddress.substring(idex,idex+1);
            if(literalMailAddress.indexOf(literal)<0){
                validate = false;
                break;
            }else{
                validate = true;
            }
        }
        return validate;
    }
    //phapnv-Start:#12660
    //phapnv-Start:#13307
    public static boolean isMailServer(String maiServer){
        String literalMailAddress = "0123456789ABCDEFGHIJKLNMOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.-";
        boolean validate = false;
        if("".equals(maiServer) || maiServer==null){
            return true;
        }
        for(int idex=0;idex<maiServer.length();idex++){
            String literal = maiServer.substring(idex,idex+1);
            if(literalMailAddress.indexOf(literal)<0){
                validate = false;
                break;
            }else{
                validate = true;
            }
        }
        return validate;
    }
    //phapnv-End:#13307

    //ngocttt
    public static String trimLeft(String string)
    {
        final int stringLength = string.length();
        int i;

        for (i = 0; i < stringLength ; i++) {
            if(!isEmpty(string.charAt(i))){
                break;
            }
        }

        if (i == 0) {
            return string;
        } else {
            return string.substring(i);
        }
    }
    public static String trimRight(String string)
    {
        final int lastChar = string.length() - 1;
        int i;

        for (i = lastChar; i >= 0 ; i--) {
            if(!isEmpty(string.charAt(i))){
                break;
            }
            /* Decrement i until it is equal to the first char that does not
             * match the trimChar given. */
        }

        if (i < lastChar) {
            // the +1 is so we include the char at i
            return string.substring(0, i+1);
        } else {
            return string;
        }
    }
    public static String trim(String string)
    {
        return trimLeft(trimRight(string));
    }

//    //#14672:S
//    /**
//     * #14672の「59」シート
//     * 問題は：PEStringUtilities.parseHtmlStringに「changeToCp943C」methodが処理される
//     * Version 2から、「changeToCp943C」methodはだめです。
//     * ⇒ StringUtils.parseHtmlStringはPEStringUtilities.parseHtmlStringの代わりに使用されている
//     * @author lochg
//     */
//    public static String parseAndToRichText(String content) {
//        content = addEmpty(content);
//        content = parseHtmlString(content);
//        content = PEStringUtilities.toRichText(content);
//        return content;
//    }
    //#14672:E
    /**「http://」「https://」「/」「\\」「<アルファベット1文字>:\」で始まらない場合はエラーとする 
     * 
     * 
     * @param url
     * @return
     */

    public static boolean isValidUrl(String url){
        if (isValidString(url)) {
            if (url.length() <= 1) {
                return false;
            }
            if ("/".equals(url.substring(0, 1))) {
                if ("/".equals(url.substring(1, 2))) {
                    return false;
                }
            } else {
                if (!url.matches("(?i)^https?://.+$")
                        && !url.matches("(?i)^\\\\{2}.+$")
                        && !url.matches("(?i)^[a-z]:.+$")) {
                    return false;
                }
            }
        }
        return true;
    }
    
//    public static int getMaxRichTextSize(){
//        FacesContext context = FacesContext.getCurrentInstance();
//        ServletContext servletContext = (ServletContext)context.getExternalContext().getContext();
//        Long maxSize = null;
//        if(servletContext.getAttribute("maxRichTextSize") != null){
//            maxSize =  (Long) servletContext.getAttribute("maxRichTextSize");     
//        }
//        int max_bytes = 0;
//        if(maxSize != null){
//            max_bytes = maxSize.intValue();
//        }
//        return max_bytes;
//    }
    
    /**
     * xu ly cat chuoi theo so byte va encoding (co the la "UTF-8" hoac SJIS) 
     * Example :
     *    cutStringByte("ああああaăあえ", 20, "UTF-8")//cat theo UTF-8, lay 20byte, ket qua = "ああああaăあ" (bi cat chu え)
     *    cutStringByte("ああああaăあえ", 20, "SJIS")//cat theo SJIS, lay 20byte, ket qua = "ああああaăあえ"
     *    
     * @param src
     * @param byteLength 
     * @return
     * @author cuongbd
     */
    public static String cutstringByte(String src, int byteLength, String encoding) {
    	if(nullOrBlank(src)){
            return "";
        }
        String result = src;
        if (countByte(src, encoding) > byteLength) {
			int bytes = 0;
			int len = src.length();
			for (int index = 0; index < len; ++index) {
				try {
					bytes += String.valueOf(src.charAt(index)).getBytes(encoding).length;
					if (bytes > byteLength){
						result = src.substring(0, index);
						break;
					}
				} catch (UnsupportedEncodingException e) {
				}
			}
        }
        return result;
    }

    /**
     * 空行以外の重複値をにチェックする為<br>
     * 例:[,A,B,C,A, ,　,あ,え, ,う,　,]
     * @param lst
     * @return true 重複、false 不重複
     */
    public static boolean isDuplicate(List<String> lst) {
        Set<String> distinctSet = new HashSet<>();
        for (int i = 0; i < lst.size(); i++) {
            if (!StringUtils.isEmpty(lst.get(i)) && !distinctSet.add(lst.get(i))) {
                return true;
            }
        }
        return false;
    }
    

    /**
     * 空行以外の重複値をにチェックする為<br>
     * 例:[,A,B,C,A, ,　,あ,え, ,う,　,]
     * @param lst
     * @return true 重複、false 不重複
     */
    public static boolean isDuplicate(String[] lst) {
        Set<String> distinctSet = new HashSet<>();
        for (int i = 0; i < lst.length; i++) {
            if (!StringUtils.isEmpty(lst[i]) && !distinctSet.add(lst[i])) {
                return true;
            }
        }
        return false;
    }
}
