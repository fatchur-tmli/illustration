package co.id.tmli.illustration.excel;

public class ExcelUtil {

    public static String getNextColumnName(String a) {
        a = a.toUpperCase();
        if (a.length() == 1) {
            if (a.equals("Z")) {
                return "AA";            
            } else {
                return (char) (a.charAt(0) + 1) + "";
            }
        } 
        final int b = (a.charAt(0) - 'A') * 26;
        final int c = a.charAt(1) - 'A';
        final int d = (b + c + 1) % (26 * 26);
        return new String(new byte[]{(byte) (d / 26 + 'A'), (byte) (d % 26 + 'A')});
    }
}
