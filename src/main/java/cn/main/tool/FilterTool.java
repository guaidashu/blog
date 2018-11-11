package cn.main.tool;

/**
 * @Author: yy
 * @Date: 18-11-11 19:56
 * @Description:
 **/
public class FilterTool {

    /**
     * @param data
     * @return A Integer converted from param(data)
     */
    public static int changeToInteger(Object data) {
        int result = 0;
        if (data instanceof Integer) {
            result = (Integer) data;
        } else {
            try {
                throw new Exception("The variable is not a Integer");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @param data
     * @return A Integer converted from param(data)
     */
    public static float changeToFloat(Object data) {
        float result = 0;
        try {
            result = new Float(data.toString());
        } catch (Exception e) {
            try {
                throw new Exception("The variable is not a Float");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return result;
    }

}
