package jiwon.airlineStatus;

import jiwon.enumset.Continent;
import jiwon.enumset.Grade;
import jiwon.enumset.Theme;
import jiwon.enumset.Way;
import yougeun.Utility;

import java.text.NumberFormat;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static jiwon.airlineStatus.AirlineInfo.city;
import static jiwon.airlineStatus.AirlineSearchView.*;
import static jiwon.enumset.Continent.*;
import static jiwon.enumset.Theme.*;
import static yougeun.Utility.*;

public class AirlineRepository {
  static List<String> airportList;
  static String from;


  // 출발지
  static {
    airportList = new ArrayList<>(
        Arrays.asList("서울/인천", "서울/김포", "제주", "광주", "여수", "청주", "대구", "부산 / 김해")
    );
  }

  // 출발지 공항 목록
  public static void airportList() {
    int i = 1;
    for (String airport : airportList) {
      System.out.println("  " + i + ". " + airport);
      i++;
    }
  }

  // 출발지 선택
  static String startingPoint(String inputFrom) {
    // 숫자 선택
    int n = Integer.parseInt(inputFrom);
    from = airportList.get(n - 1);
    return from;
  }


  //도착지 선택
  public void choiceCity(Object o) {
      while (true) {

          makeLine();
          ThemeAndContinent(o);
          String pick = input("여행을 떠나고 싶은 나라를 선택해주세요");
          makeLine();
          tk.setDestination(pick);
          if (!pickCity(pick)) {
              System.out.println("나라를 정확히 입력해주세요!");
              Utility.stop();
              continue;
          }
          break;

      }

  }


  // 테마, 나라별 여행지 선택
  private void ThemeAndContinent(Object o) {
    city.stream()
        .filter(t -> t.getChoice(o) == o)
        .collect(toList())
        .forEach(a -> showTicketInfo(a));
  }

  public boolean pickCity(String input) {
    try {
        City findCity = city.stream()
                .filter(c -> c.getCountryName().equals(input))
                .findFirst().get();
        showTicketInfo(findCity);
//        System.out.println("findCity = " + findCity);
        return true;
    } catch (Exception e){
        return false;
    }
  }

  public void showTicketInfo(City t) {
    System.out.println("    " + from + " <-> " + t.getCountryName());
//    System.out.println("    "+(pickWay==Way.ONE_WAY?"편도":"왕복"));
    System.out.println("    " + tk.getWayK());
    int fee = (int) calFee(t);
    if (t.getTheme() == DISCOUNT) {
      fee *= 0.7;
//      System.out.println("30퍼 할인");
    }

    tk.setPay(fee);
    System.out.println("    KRW " + NumberFormat.getInstance(Locale.getDefault()).format(fee) );
    makeLine();
  }

  double calFee(City t) {
    double fee = t.getFee() * n // 인원수
        * (tk.getWay() == Way.ONE_WAY ? 1 : 2) // 편도 왕복 계산
        * multGrade();
    if (test.equals("ㅇㅇ")){
      fee=t.getFee();
    }

    return fee;
  }

  private double multGrade() {
    if (tk.getGrade() == Grade.PRESTIGE) return 1.5;
    else if (tk.getGrade() == Grade.FIRST) return 2;
    return 1;
  }

  public Theme themeChangeNum(String inputTheme) {
    switch (inputTheme) {
      case "1":
        return SEASON;
      case "2":
        return DISCOUNT;
      case "3":
        return UNUSUAL;
    }
    return NORMAL;
  }

  //나라별 여행지 ( 모든도시 보여주기)
  public Continent pickContinent(String s) {
    Map<String, Continent> cMap = new HashMap<>();
    cMap.put("0", DOMESTIC);
    cMap.put("1", CHINA);
    cMap.put("2", JAPAN);
    cMap.put("3", SOUTHEAST_ASIA);
    cMap.put("4", AMERICA);
    cMap.put("5", EUROPE);
    cMap.put("6", OCEANIA);

    return cMap.get(s);
  }

  public void way(String input) {
    switch (input) {
      case "1":
      case "왕복":
        tk.setWay(Way.ROUND_TRIP);
        break;
      case "2":
      case "편도":
        tk.setWay(Way.ONE_WAY);
        break;
      default:
        System.out.println("숫자를 정확히 입력해주세요");
    }
  }

  public void seatGrade(String n) {
    switch (n) {
      case "1":
        tk.setGrade(Grade.ECONOMY);
        break;
      case "2":
        tk.setGrade(Grade.PRESTIGE);
        break;
      case "3":
        tk.setGrade(Grade.FIRST);
        break;
      default:
        System.out.println("숫자를 정확히 입력해주세요");
    }
  }
}

