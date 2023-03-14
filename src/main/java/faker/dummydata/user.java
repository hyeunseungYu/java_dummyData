package faker.dummydata;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class user {
    public static void main(String[] args) {

        String csvFile = "/Users/yuhyeonseung/Desktop/file.csv";
        FileWriter writer = null;

        try {
            writer = new FileWriter(csvFile);


            // CSV 데이터 작성
            List<List<String>> data = new ArrayList<>();


            int length = 1001;

            for (int i = 1; i < length; i++) {
                data.add( Arrays.asList("tester" + i, "abcd1234?"));
            }

            //위에서 추가된 애들에서 안에 들어있는 elements를 하나의 string으로 묶되 ,로 구분하고 개행
            for (List<String> row : data) {
                String rowStr = String.join(",", row);
                writer.write(rowStr + "\n");
            }

            System.out.println("CSV file created successfully!");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}