package hcmuaf.nlu.edu.vn.quanlyxemphim.util;



import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VNPAYConfig {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = VNPAYConfig.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.config");


            }
            // Load các thuộc tính từ file config.properties
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static final String TMNCODE = properties.getProperty("vnp_TmnCode");
    public static final String HASH_SECRET = properties.getProperty("vnp_HashSecret");
    public static final String VNP_URL = properties.getProperty("vnp_Url");
    public static final String RETURN_URL = properties.getProperty("vnp_ReturnUrl");

}
