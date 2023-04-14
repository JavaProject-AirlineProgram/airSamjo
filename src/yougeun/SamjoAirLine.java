package yougeun;

import jiwon.airlineStatus.AirlineSearchView;
import yougeun.Client.Client;
import yougeun.Client.ClientRepository;
import yougeun.airlineInfo.AirlineInfoView;
import yougeun.board.BoardView;
import yougeun.checkin.SeatView;
import yougeun.couponShop.CouponShop;
import yougeun.myflight.MyPage;
import yougeun.payment.Payment;
import yougeun.reservationCheck.ReservationCheck;
import yougeun.schedule.ScheduleFareCheck;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static yougeun.Utility.*;


public class SamjoAirLine {





    // 메인 메뉴 화면입니다!
    public static void menu(Client client){
        Utility.topBar();
        System.out.println("✦── 삼조 에어라인에 오신 "+ client.getUserName()+"님 환영합니다 ──");
        System.out.println("┆ 1. 국내선 예매");    // 지원님
        System.out.println("✿2. 국제선 예매");    // 지원님
        System.out.println("┆ 3. 공항안내");       // 유근
        // 공항선택, 공항정보, 체크인, 라운지
        System.out.println("┆ 4. 공지사항");       // 유근
        // 게시판 형식의 프로그램
        System.out.println("┆ 5. 체크인");       // 유근
        System.out.println("✿6. 예약 조회");    // 유근
        System.out.println("┆ 7. 마일리지 쇼핑몰");  //유근
        System.out.println("┆ 8. My FLIGHT");     // 유근
        // 개인 자산 관리, 포인트 관리 ...
        System.out.println("┆ 9. 스케줄/운임 조회"); // 유근
        System.out.println("✿0. 로그아웃");      // 동우님
        System.out.println("╰───────────────\uD83E\uDD40");
    }


    /**
     * 번호에 따라 원하는 메뉴를 실행하는 함수입니다.
     * @param selectMenu 유저로 부터 입력을 받은 변수
     */
    public static boolean mainLogic(int selectMenu, Client client){

        switch (selectMenu){
            case 1:
                AirlineSearchView.searchView(client, true);
//                System.out.println("국내선 예매");
                stop();
                break;
            case 2:
                AirlineSearchView.searchView(client, false);
//                Payment.pay(client);
//                System.out.println("결제");
                stop();
                break;
            case 3:
//                System.out.println("공항 안내");
                AirlineInfoView.selectAirline();
//                stop();
                break;
            case 4:
                BoardView.run();
                break;
            case 5:
                SeatView.checkin(client);
                stop();
                break;
            case 6:
//                System.out.println("예약 조회");
                ReservationCheck.check();
                stop();
                break;
            case 7:
//                System.out.println("마일리지 쇼핑몰");
                CouponShop.shop(client);
                stop();
                break;
            case 8:
//                System.out.println("My Flight");
                MyPage.myPageMenu(client);
                stop();
                break;
            case 9:
                System.out.println("스케줄 운임 조회");
                ScheduleFareCheck.menu();
                stop();
                break;
            case 0:
                System.out.println("로그아웃");
                return false;
            default:
                inputError();
                System.out.println("지원되는 번호 : [1번~0번]");
                stop();
        }
        return true;

    }
    // 메인 실행 함수
    public static void run(Client client){
        while (true) {
            menu(client);
            try {
                int selectMenu = Integer.parseInt(input("원하는 메뉴를 선택하세요 : "));
                if(!mainLogic(selectMenu, client)){
                    return;
                }
            } catch (Exception e) {
                inputError();
            }

        }
    }


}
