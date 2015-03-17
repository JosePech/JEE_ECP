package utils;

public class Converter {
    
    public static Integer parseInt(Object str) {
        Integer n = null;
        try {
            n = new Integer(Integer.parseInt(str.toString()));
        } catch (Exception ex) {
        }
        return n;
    }


}
