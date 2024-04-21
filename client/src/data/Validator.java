package data;

import exceptions.WrongArgumentException;

public class Validator {

    public static void inputIsNotEmpty(String arg, String data) throws WrongArgumentException {
        if (arg.isEmpty() || arg.trim().isEmpty()) {
            throw new WrongArgumentException(data);
        }
    }

    public static void heightIsOk(String arg) throws WrongArgumentException {
        try {
            Double x = Double.parseDouble(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("height");
        }
    }

    public static void coordinateXIsOk(String arg) throws WrongArgumentException {
        try {
            int x = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("X");
        }
    }

    public static void coordinateYIsOk(String arg) throws WrongArgumentException {
        try {
            int y = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException("Y");
        }
    }

    public static void locationXIsOk(String arg) throws WrongArgumentException {
        try {
            int x = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new WrongArgumentException(arg);
        }
    }

    public static void locationYIsOk(String arg) throws WrongArgumentException {
        try {
            double y = Double.parseDouble(arg);
        } catch (Exception e) {
            throw new WrongArgumentException(arg);
        }
    }

    public static void locationZIsOk(String arg) throws WrongArgumentException {
        try {
            float y = Float.parseFloat(arg);
        } catch (Exception e) {
            throw new WrongArgumentException(arg);
        }
    }

    public static void countryTypeIsOk(String arg) throws WrongArgumentException {
        try {
            int n = Integer.parseInt(arg);
            if (n < 1 || n > 3) {
                throw new WrongArgumentException("CountryType");
            }
        } catch (Exception e) {
            throw new WrongArgumentException("CountryType");
        }
    }

    public static void eyeTypeIsOk(String arg) throws WrongArgumentException {
        try {
            int n = Integer.parseInt(arg);
            if (n < 1 || n > 3) {
                throw new WrongArgumentException("EyeType");
            }
        } catch (Exception e) {
            throw new WrongArgumentException("EyeType");
        }
    }

    public static void hairTypeIsOk(String arg) throws WrongArgumentException {
        try {
            int n = Integer.parseInt(arg);
            if (n < 1 || n > 4) {
                throw new WrongArgumentException("HairType");
            }
        } catch (Exception e) {
            throw new WrongArgumentException("HairType");
        }
    }
}


