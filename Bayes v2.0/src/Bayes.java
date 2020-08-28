import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Bayes extends JComponent {

    public void paintComponent(Graphics g){
        super.paintComponents(g);
        Graphics2D g2d=(Graphics2D)g;

        g2d.drawLine(40, 40, 40, 340);
        g2d.drawLine(40, 340, 340, 340);
        g2d.drawString("1/3", 12, 45);
        g2d.drawString("N", 340, 355);
        g2d.drawString("0",30,355);

        int Xcord = 0;
        for(int i=1; i<10;i++){
            Xcord+=30;
            g2d.drawLine(40 + Xcord,345,40 + Xcord,335);
            g2d.drawString(""+(i*100),37+Xcord,358);
            g2d.drawLine(35,40+Xcord,45,40+Xcord);
            g2d.drawString(""+(i*10),17,344-Xcord);
        }
        g2d.setPaint(Color.BLACK);
        ArrayList<Integer> savePoints = new ArrayList<>();
        float MusicClass = 0.33f; // Класс Музыка
        float BasketbollClass = 0.33f; // Класс Баскетбол
        float ScienceClass = 0.33f; // Класс Наука
        // Тест №1 для 100 новостей (по 33 каждого из сласса)
        // лист для хранения информации из файла music
        ArrayList<String> musicList_1 = null;
        try {
            musicList_1 = createArray(new FileInputStream("input data - 100 music.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла basketball
        ArrayList<String> basketbollList_1 = null;
        try {
            basketbollList_1 = createArray(new FileInputStream("input data - 100 basketboll.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла science
        ArrayList<String> scienceList_1 = null;
        try {
            scienceList_1 = createArray(new FileInputStream("input data - 100 science.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // создаем vocabulary для определение сколько раз стречается каждое слово
        HashMap<String, Integer> vocabulary_1 = createHashMap(musicList_1, basketbollList_1, scienceList_1);

        // все слова которые встречаются меньше 1 раза удаляются так как они не помогут
        Iterator<Map.Entry <String, Integer>> iterator_1 = vocabulary_1.entrySet().iterator();
        while (iterator_1.hasNext()) {
            if (iterator_1.next().getValue().equals(1)) {
                iterator_1.remove();
            }
        }

        // создаем HashMap music с вероятностями класса Music
        HashMap<String, Double> music_1 = createHashMapNumberOfCopies(vocabulary_1, musicList_1, MusicClass);
        HashMap<String, Double> basketboll_1 = createHashMapNumberOfCopies(vocabulary_1, basketbollList_1, BasketbollClass);
        HashMap<String, Double> science_1 = createHashMapNumberOfCopies(vocabulary_1, scienceList_1, ScienceClass);
        //System.out.println("Идёт тестирование. Пожалуйста не мешайте!");
        //точка №1 для 100 элементов
        double a1 = 0;
        try {
            a1 = doPerсent("test data - 100 music.txt", "test data - 100 basketboll.txt", "test data - 100 science.txt", music_1, basketboll_1, science_1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long l1 = Math.round(a1*100);
        int a11 = (int) l1;
        savePoints.add(a11);
        System.out.println("a1 = " + Math.round(a1*100));


        // Тест №2 для 200 новостей (по 66 каждого из сласса)
        // лист для хранения информации из файла music
        ArrayList<String> musicList_2 = null;
        try {
            musicList_2 = createArray(new FileInputStream("input data - 200 music.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла basketball
        ArrayList<String> basketbollList_2 = null;
        try {
            basketbollList_2 = createArray(new FileInputStream("input data - 200 basketboll.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла science
        ArrayList<String> scienceList_2 = null;
        try {
            scienceList_2 = createArray(new FileInputStream("input data - 200 science.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // создаем vocabulary для определение сколько раз стречается каждое слово
        HashMap<String, Integer> vocabulary_2 = createHashMap(musicList_2, basketbollList_2, scienceList_2);

        // все слова которые встречаются меньше 1 раза удаляются так как они не помогут
        Iterator<Map.Entry <String, Integer>> iterator_2 = vocabulary_2.entrySet().iterator();
        while (iterator_2.hasNext()) {
            if (iterator_2.next().getValue().equals(1)) {
                iterator_2.remove();
            }
        }

        // создаем HashMap music с вероятностями класса Music
        HashMap<String, Double> music_2 = createHashMapNumberOfCopies(vocabulary_2, musicList_2, MusicClass);
        HashMap<String, Double> basketboll_2 = createHashMapNumberOfCopies(vocabulary_2, basketbollList_2, BasketbollClass);
        HashMap<String, Double> science_2 = createHashMapNumberOfCopies(vocabulary_2, scienceList_2, ScienceClass);
        //System.out.println("Идёт тестирование. Пожалуйста не мешайте!");
        //точка №2 для 200 элементов
        double a2 = 0;
        try {
            a2 = doPerсent("test data - 200 music.txt", "test data - 200 basketboll.txt", "test data - 200 science.txt", music_2, basketboll_2, science_2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long l2 = Math.round(a2*100);
        int a22 = (int) l2;
        savePoints.add(a22);
        System.out.println("a2 = " + Math.round(a2*100));


        // Тест №3 для 400 новостей (по 132 каждого из сласса)
        // лист для хранения информации из файла music
        ArrayList<String> musicList_3 = null;
        try {
            musicList_3 = createArray(new FileInputStream("input data - 400 music.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла basketball
        ArrayList<String> basketbollList_3 = null;
        try {
            basketbollList_3 = createArray(new FileInputStream("input data - 400 basketboll.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла science
        ArrayList<String> scienceList_3 = null;
        try {
            scienceList_3 = createArray(new FileInputStream("input data - 400 science.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // создаем vocabulary для определение сколько раз стречается каждое слово
        HashMap<String, Integer> vocabulary_3 = createHashMap(musicList_3, basketbollList_3, scienceList_3);

        // все слова которые встречаются меньше 1 раза удаляются так как они не помогут
        Iterator<Map.Entry <String, Integer>> iterator_3 = vocabulary_3.entrySet().iterator();
        while (iterator_3.hasNext()) {
            if (iterator_3.next().getValue().equals(1)) {
                iterator_3.remove();
            }
        }

        // создаем HashMap music с вероятностями класса Music
        HashMap<String, Double> music_3 = createHashMapNumberOfCopies(vocabulary_3, musicList_3, MusicClass);
        HashMap<String, Double> basketboll_3 = createHashMapNumberOfCopies(vocabulary_3, basketbollList_3, BasketbollClass);
        HashMap<String, Double> science_3 = createHashMapNumberOfCopies(vocabulary_3, scienceList_3, ScienceClass);
        //System.out.println("Идёт тестирование. Пожалуйста не мешайте!");
        //точка №2 для 200 элементов
        double a3 = 0;
        try {
            a3 = doPerсent("test data - 400 music.txt", "test data - 400 basketboll.txt", "test data - 400 science.txt", music_3, basketboll_3, science_3);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long l3 = Math.round(a3*100);
        int a32 = (int) l3;
        savePoints.add(a32);
        System.out.println("a3 = " + Math.round(a3*100));

        // Тест №4 для 800 новостей (по 266 каждого из сласса)
        // лист для хранения информации из файла music
        ArrayList<String> musicList_4 = null;
        try {
            musicList_4 = createArray(new FileInputStream("input data - 800 music.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла basketball
        ArrayList<String> basketbollList_4 = null;
        try {
            basketbollList_4 = createArray(new FileInputStream("input data - 800 basketboll.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // лист для хранения информации из файла science
        ArrayList<String> scienceList_4 = null;
        try {
            scienceList_4 = createArray(new FileInputStream("input data - 800 science.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // создаем vocabulary для определение сколько раз стречается каждое слово
        HashMap<String, Integer> vocabulary_4 = createHashMap(musicList_4, basketbollList_4, scienceList_4);

        // все слова которые встречаются меньше 1 раза удаляются так как они не помогут
        Iterator<Map.Entry <String, Integer>> iterator_4 = vocabulary_4.entrySet().iterator();
        while (iterator_4.hasNext()) {
            if (iterator_4.next().getValue().equals(1)) {
                iterator_4.remove();
            }
        }

        // создаем HashMap music с вероятностями класса Music
        HashMap<String, Double> music_4 = createHashMapNumberOfCopies(vocabulary_4, musicList_4, MusicClass);
        HashMap<String, Double> basketboll_4 = createHashMapNumberOfCopies(vocabulary_4, basketbollList_4, BasketbollClass);
        HashMap<String, Double> science_4 = createHashMapNumberOfCopies(vocabulary_4, scienceList_4, ScienceClass);
        //System.out.println("Идёт тестирование. Пожалуйста не мешайте!");
        //точка №2 для 200 элементов
        double a4 = 0;
        try {
            a4 = doPerсent("test data - 800 music.txt", "test data - 800 basketboll.txt", "test data - 800 science.txt", music_4, basketboll_4, science_4);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        long l4 = Math.round(a4*100);
        int a42 = (int) l4;
        savePoints.add(a42);
        System.out.println("a4 = " + Math.round(a4*100));


        int[] array = {1,2,4,8};
        for(int i=0; i<savePoints.size(); i++){
            g2d.fillRect(35 + ( 30 * array[i]),335 - (3 * savePoints.get(i)),10,10); //11
        }
        super.repaint();
    }

    // открываем файл .txt для того что бы записать все предложения в ArrayList<String>
    private  static ArrayList<String> createArray(FileInputStream file){
        ArrayList<String> list = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            String s = scanner.next();
            list.add(s.toLowerCase());
        }
        return list;
    }
    // Создаем словарь слов и количество их повторений
    private  static  HashMap<String, Integer> createHashMap (ArrayList<String> musicList, ArrayList<String> basketbollList, ArrayList<String> scienceList){
        HashMap<String, Integer> vocabulary = new HashMap<>();
        ArrayList<ArrayList<String>> list = new ArrayList();
        list.add(musicList);
        list.add(basketbollList);
        list.add(scienceList);
        for(int i=0; i<list.size(); i++) {
            for (String s : list.get(i)) {
                if (vocabulary.containsKey(s))
                    vocabulary.put(s, vocabulary.get(s) + 1);
                else
                    vocabulary.put(s, 1);
            }
        }
        return vocabulary;
    }
    // Создаем словарь слов с нормированными вероятностями классов
    private  static  HashMap<String, Double> createHashMapNumberOfCopies(HashMap<String, Integer> vocabulary, ArrayList<String> list, float musicvalue){
        HashMap<String, Integer> map = new HashMap<>();
        for (Map.Entry<String, Integer> pair : vocabulary.entrySet()) {
            for(int i=0; i<list.size();i++){
                if(list.get(i).equals(pair.getKey())){
                    if (map.containsKey(list.get(i)))
                        map.put(list.get(i),map.get(list.get(i))+1);
                    else
                        map.put(list.get(i),1);
                }
            }
        }
        HashMap<String, Double > listDouble = new HashMap<>();
        for (Map.Entry<String, Integer> pair : vocabulary.entrySet()) {
            listDouble.put(pair.getKey(), 0.0);
        }
        for (Map.Entry<String, Integer> pair : vocabulary.entrySet()) {
            for (Map.Entry<String, Integer> couple : map.entrySet()) {
                if(couple.getKey().equals(pair.getKey())){
                    listDouble.put(pair.getKey(), (double)couple.getValue()/(double)pair.getValue());
                }
            }
        }
        HashMap<String, Double > listHash = new HashMap<>();
        for (Map.Entry<String, Integer> pair : vocabulary.entrySet()) {
            for (Map.Entry<String, Double> couple : listDouble.entrySet()) {
                if(couple.getKey().equals(pair.getKey())){
                    listHash.put(pair.getKey(), (pair.getValue() * couple.getValue() + musicvalue) / (pair.getValue()+1));
                }
            }
        }
        return  listHash;
    }

    private static ArrayList<Double>  probabilityOfClass(HashMap<String, Double> map, ArrayList<String> list){
        double number = 0.0;
        ArrayList<Double> outputList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).equals(".")) {
                for (Map.Entry<String, Double> pair : map.entrySet()) {
                    if (list.get(i).equals(pair.getKey())) {
                        number += log2n(pair.getValue());
                    }
                }
            }
            else if (list.get(i).equals(".")) {
                outputList.add(number);
                number=0;
            }
        }
        return outputList;
    }

    private static double doPerсent(String file1, String file2, String file3, HashMap<String, Double> music, HashMap<String, Double> basketboll, HashMap<String, Double> science) throws FileNotFoundException {
        ArrayList<String> testMusicList = createArray(new FileInputStream(file1));
        ArrayList<Double> outputMusicString_1 = probabilityOfClass(music, testMusicList);
        ArrayList<Double> outputBasketbollString_1 = probabilityOfClass(basketboll, testMusicList);
        ArrayList<Double> outputScienceString_1 = probabilityOfClass(science, testMusicList);
        ArrayList<String> result_1 = new ArrayList<>();
        for(int i=0; i<outputMusicString_1.size(); i++){
            String s = max(outputMusicString_1.get(i), outputBasketbollString_1.get(i), outputScienceString_1.get(i));
            result_1.add(s);
        }

        int count1=0;
        for(int i=0; i<result_1.size(); i++){
            if(!result_1.get(i).equals("Music"))
                count1++;
        }


        ArrayList<String> testBasketbollList = createArray(new FileInputStream(file2));
        ArrayList<Double> outputMusicString_2 = probabilityOfClass(music, testBasketbollList);
        ArrayList<Double> outputBasketbollString_2 = probabilityOfClass(basketboll, testBasketbollList);
        ArrayList<Double> outputScienceString_2 = probabilityOfClass(science, testBasketbollList);

        ArrayList<String> result_2 = new ArrayList<>();
        for(int i=0; i<outputMusicString_2.size(); i++){
            String s = max(outputMusicString_2.get(i), outputBasketbollString_2.get(i), outputScienceString_2.get(i));
            result_2.add(s);
        }

        int count2=0;
        for(int i=0; i<result_2.size(); i++){
            if(!result_2.get(i).equals("Basketboll"))
                count2++;
        }

        ArrayList<String> testScienceList = createArray(new FileInputStream(file3));
        ArrayList<Double> outputMusicString_3 = probabilityOfClass(music, testScienceList);
        ArrayList<Double> outputBasketbollString_3 = probabilityOfClass(basketboll, testScienceList);
        ArrayList<Double> outputScienceString_3 = probabilityOfClass(science, testScienceList);

        ArrayList<String> result_3 = new ArrayList<>();
        for(int i=0; i<outputMusicString_3.size(); i++){
            String s = max(outputMusicString_3.get(i), outputBasketbollString_3.get(i), outputScienceString_3.get(i));
            result_3.add(s);
        }

        int count3=0;
        for(int i=0; i<result_3.size(); i++){
            if(!result_3.get(i).equals("Science"))
                count3++;
        }
        int sumCount = count1 + count2 + count3;
        int sumArray = result_1.size() + result_2.size() + result_3.size();
        double perCent = ((sumArray - sumCount)*1.0)/sumArray;
        return perCent;
    }

    private static double  log2n(double b) {
        return Math.log(b) / Math.log(2);
    }

    public static String max(double a, double b, double c) {
        if (a >= b && a >= c) {
            return "Music";
        } else if (b >= a && b >= c) {
            return "Basketboll";
        } else return "Science";
    }

}
