package hcmuaf.nlu.edu.vn.quanlyxemphim.util;

import jakarta.servlet.http.HttpServletRequest;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.TreeMap;

public class VNPayUtil {
    public static String createPaymentUrl(HttpServletRequest request, double amount) {
        long vnp_Amounts = Math.round(amount * 100); // Lấy giá trị nguyên từ số thập phân
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String vnp_OrderInfo = "Thanh toan don hang";
        String vnp_OrderType = "other";
        String vnp_Amount = String.valueOf(vnp_Amounts);
        String vnp_CurrCode = "VND";
        String vnp_Locale = "vn";
        String vnp_TxnRef = UUID.randomUUID().toString();

        // Thời gian tạo giao dịch
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Thời gian hết hạn (15 phút sau thời gian tạo)
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());

        String vnp_IpAddr = request.getRemoteAddr();

        // Tạo TreeMap để sắp xếp tham số theo thứ tự alphabet
        TreeMap<String, String> params = new TreeMap<>();
        params.put("vnp_Version", vnp_Version);
        params.put("vnp_Command", vnp_Command);
        params.put("vnp_TmnCode", VNPAYConfig.TMNCODE);
        params.put("vnp_Amount", vnp_Amount);
        params.put("vnp_CurrCode", vnp_CurrCode);
        params.put("vnp_TxnRef", vnp_TxnRef);
        params.put("vnp_OrderInfo", vnp_OrderInfo);
        params.put("vnp_OrderType", vnp_OrderType);
        params.put("vnp_ReturnUrl", VNPAYConfig.RETURN_URL);
        params.put("vnp_CreateDate", vnp_CreateDate);
        params.put("vnp_ExpireDate", vnp_ExpireDate);
        params.put("vnp_IpAddr", vnp_IpAddr);
        params.put("vnp_Locale", vnp_Locale);

        // Tạo chuỗi tham số query từ TreeMap
        StringBuilder query = new StringBuilder();
        for (String key : params.keySet()) {
            try {
                // URL Encode các giá trị tham số để tránh lỗi khi có ký tự đặc biệt
                String encodedValue = URLEncoder.encode(params.get(key), StandardCharsets.UTF_8.toString());
                query.append(key).append("=").append(encodedValue).append("&");
            } catch (Exception e) {
                throw new RuntimeException("Error encoding parameter value: " + e.getMessage(), e);
            }
        }

        // Cắt bỏ dấu & cuối cùng
        if (query.length() > 0) {
            query.deleteCharAt(query.length() - 1);
        }

        // Tạo chữ ký
        String vnp_SecureHash = hmacSHA512(VNPAYConfig.HASH_SECRET, query.toString());

        // Trả về URL thanh toán
        return VNPAYConfig.VNP_URL + "?" + query + "&vnp_SecureHash=" + vnp_SecureHash;
    }

    private static String hmacSHA512(String key, String data) {
        try {
            // Khởi tạo HMAC SHA512
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");

            // Khởi tạo khóa bí mật
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
            hmacSHA512.init(secretKey);

            // Tính toán hash
            byte[] hash = hmacSHA512.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // Chuyển đổi kết quả thành chuỗi Hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Error creating HMAC SHA512 hash", e);
        }
    }
}
