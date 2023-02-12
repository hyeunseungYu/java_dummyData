package faker.dummydata;


import net.datafaker.Faker;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class dummy {
    public static void main(String[] args) {
        System.out.println(" Start time: "+  new Timestamp(System.currentTimeMillis()));
        // 지역을 한국어로 변경
        Faker faker = new Faker(new Locale("ko-KR"));

        List<Product> products = new ArrayList<>();

        // 10,000,000개의 더미 데이터 생성
        for(long i = 1; i <= 10000000; i++) {
            // 제품 ID 생성
            Long productId = i;

            // 랜덤 이름 생성
            String name = faker.commerce().productName();

            // 랜덤 주소 생성
            String location = faker.address().cityName();

            // 랜덤 20자 이내 string 생성
            String around = "주변" + faker.number().numberBetween(1, 100);
            String notice = "공지" + faker.number().numberBetween(1, 100);
            String base = "기본" + faker.number().numberBetween(1, 100);

            // 랜덤 가격 생성 (100 단위 이하의 숫자 제외)
            int price = faker.number().numberBetween(20000, 120000) / 100 * 100;

            // imgUrl 고정
            Long imgUrl = 1L;

            // 현재 날짜 생성
            Date date = new Date();
            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateToString = simpleDateFormat.format(date);



            // 데이터를 products 리스트에 저장
            products.add(new Product(productId, name, location, around, notice, base, price, imgUrl, dateToString));
        }

       String CSV_FILE = "products.csv";

        // csv 파일로 만들기
        try (FileWriter writer = new FileWriter(CSV_FILE)) {
            writer.append("product_id");
            writer.append(",");
            writer.append("name");
            writer.append(",");
            writer.append("location");
            writer.append(",");
            writer.append("around");
            writer.append(",");
            writer.append("notice");
            writer.append(",");
            writer.append("base");
            writer.append(",");
            writer.append("price");
            writer.append(",");
            writer.append("imgUrl");
            writer.append(",");
            writer.append("date");
            writer.append("\n");

            for (Product product : products) {
                writer.append(String.valueOf(product.getProductId()));
                writer.append(",");
                writer.append(product.getName());
                writer.append(",");
                writer.append(product.getLocation());
                writer.append(",");
                writer.append(product.getAround());
                writer.append(",");
                writer.append(product.getNotice());
                writer.append(",");
                writer.append(product.getBase());
                writer.append(",");
                writer.append(String.valueOf(product.getPrice()));
                writer.append(",");
                writer.append(String.valueOf(product.getImgUrl()));
                writer.append(",");
                writer.append(product.getDate());
                writer.append("\n");
            }

            System.out.println("Products successfully written to CSV file: " + CSV_FILE);
        } catch (IOException e) {
            System.out.println(" error time: "+  new Timestamp(System.currentTimeMillis()));
            e.printStackTrace();
        }

        System.out.println(" end time: "+  new Timestamp(System.currentTimeMillis()));

    }



}


class Product {
    private Long productId;
    private String name;
    private String location;
    private String around;
    private String notice;
    private String base;
    private int price;
    private Long imgUrl;
    private String date;

    public Product(Long productId, String name, String location, String around, String notice, String base, int price, Long imgUrl, String date) {
        this.productId = productId;
        this.name = name;
        this.location = location;
        this.around = around;
        this.notice = notice;
        this.base = base;
        this.price = price;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getAround() {
        return around;
    }

    public String getNotice() {
        return notice;
    }

    public String getBase() {
        return base;
    }

    public int getPrice() {
        return price;
    }

    public Long getImgUrl() {
        return imgUrl;
    }

    public String getDate() {
        return date;
    }
}
