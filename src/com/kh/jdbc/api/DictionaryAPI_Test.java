package com.kh.jdbc.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;

public class DictionaryAPI_Test {
    private static final String API_URL = "https://stdict.korean.go.kr/api/search.do";
    private static final String API_KEY = "761160F9AF196EA84C25C14E90C05B63";

    public static void main(String[] args) {
        String word = "나나나"; // 검색할 단어

        try {
            // 1. 단어 URL 인코딩
            String encodedWord = URLEncoder.encode(word, "UTF-8");
            String urlStr = API_URL + "?key=" + API_KEY + "&q=" + encodedWord + "&req_type=json";
            URL url = new URL(urlStr);

            // 2. HTTP 요청
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // 3. 응답 읽기
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // 응답 내용 출력 (디버깅용)
                System.out.println("API 응답: " + response.toString());

                // 응답이 JSON 형식인지 확인
                if (response.toString().startsWith("{")) {
                    // 4. JSON 파싱
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    JSONObject channel = jsonResponse.getJSONObject("channel");

                    if (channel.has("item")) {
                        JSONArray items = channel.getJSONArray("item");

                        // item 배열이 비어있는지 확인
                        if (items.length() > 0) {
                            // 첫 번째 item만 가져오기
                            JSONObject item = items.getJSONObject(0);
                            String foundWord = item.getString("word");

                            // sense 객체 추출
                            if (item.has("sense")) {
                                JSONObject sense = item.getJSONObject("sense");

                                // definition 키 확인
                                if (sense.has("definition")) {
                                    String definition = sense.getString("definition");
                                    System.out.println("단어: " + foundWord);
                                    System.out.println("설명: " + definition);
                                } else {
                                    System.out.println("단어: " + foundWord);
                                    System.out.println("설명이 없습니다.");
                                }
                            } else {
                                System.out.println("단어: " + foundWord);
                                System.out.println("sense 정보가 없습니다.");
                            }
                        } else {
                            System.out.println("검색 결과가 없습니다.");
                        }
                    } else {
                        System.out.println("검색 결과가 없습니다.");
                    }
                } else {
                    System.out.println("응답이 JSON 형식이 아닙니다.");
                }
            } else {
                System.out.println("API 호출 실패, 응답 코드: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
