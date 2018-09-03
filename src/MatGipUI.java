import java.util.ArrayList;
import java.util.Scanner;

public class MatGipUI {

    private Scanner scanner;
    private MatGipService service;

    public MatGipUI(MatGipService service) {
        this.service = service;
        this.scanner = new Scanner(System.in);
    }

    public void init(){
        System.out.println("1번 등록, 2번 검색, 3번 검색2, -1.종료");
        int oper = Integer.parseInt(scanner.nextLine());
        if(oper == -1){
            return;
        }
        if(oper == 1){
            registerUI();
        }else if(oper == 2){
            findUI();
        }else if(oper == 3){
            findAny();
        }
        init();
    }

    private void findAny() {

        String oper = input("메뉴m, 상호n 로 찾을까요? ");

        String keyword = input("키워드");

        if(oper.equals("m")){
            service.findByAny(keyword, vo -> vo.checkMenu(keyword));
        }else if(oper.equals("n")){
            service.findByAny(keyword, vo -> vo.checkName(keyword));
        }
    }

    private String input(String msg){
        System.out.println(msg);
        return scanner.nextLine();
    }

    private void registerUI(){
        String place = input("어떤 동네에 있나요?");
        String name = input("가게 이름은요?");
        String menu = input("몇 파나요?");

        MatGipVO vo = new MatGipVO(place, name, menu);
        service.register(vo);
        System.out.println("새로운 맛집이 등록되었습니다.");

    }

    private void findUI(){

        System.out.println("find..........");
        String place = input("어느 동네 맛집을 찾나요?");

        service.findByPlace2(place).forEach(vo -> System.out.println(vo));

    }
}
