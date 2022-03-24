package advisor;

import java.util.ArrayList;

public class View {
    private static int currentPage;
    private static int lastPage;
    private static final int itemsOnPage = Controller.page;
    private  static int items;
    private static ArrayList<String> currentList;

    public static void show(GetArray list) {
        currentList = list.getArray();
        currentPage = 1;
        items = currentList.size();
        lastPage = (int)Math.ceil((double)items / itemsOnPage);
        for (int i = 0 ; i < currentPage * itemsOnPage && i < items; i++ ) {
            System.out.println(currentList.get(i));
        }
        System.out.println("---PAGE " + currentPage + " OF " + lastPage + "---");
    }

    public static void showNext() {
        if(currentPage < lastPage && currentList != null) {
            currentPage++;
            for (int i = (currentPage - 1) * itemsOnPage ; i < currentPage * itemsOnPage && i < items; i++ ) {
                System.out.println(currentList.get(i));
            }
            System.out.println("---PAGE " + currentPage + " OF " + lastPage + "---");
        }else {
            System.out.println("No more pages.");
        }
    }

    public static void showPrev() {
        if(currentPage > 1 && currentList != null) {
            currentPage--;
            for (int i = (currentPage - 1) * itemsOnPage ; i < currentPage * itemsOnPage && i < items; i++ ) {
                System.out.println(currentList.get(i));
            }
            System.out.println("---PAGE " + currentPage + " OF " + lastPage + "---");
        }else {
            System.out.println("No more pages.");
        }
    }
}
