package test.datastructures.stack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;

public class SimpleTextEditor {
    private static String currentString = "";
    private static ArrayDeque<String> stack = new ArrayDeque<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static BufferedWriter writer;
    private static final String skipRegex = "(\r\n|[\n\r\u2028\u2029\u0085])?";

    public static void main(String... ar) throws IOException {
        writer = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        int n = scanner.nextInt();
        ArrayDeque<Action> actions = new ArrayDeque<>(n);

        for (int i = 0; i < n; i++) {
            scanner.skip(skipRegex);
            String line = scanner.nextLine();
            String[] stringItems = line.split(" ");
            Integer actionNumber = Integer.valueOf(stringItems[0]);
            switch (actionNumber) {
                case 4:
                    actions.addLast(new UndoAction());
                    break;
                case 3:
                    Integer elementForPrint = Integer.valueOf(stringItems[1]);
                    actions.addLast(new PrintAction(elementForPrint));
                    break;
                case 2:
                    Integer countToDelete = Integer.valueOf(stringItems[1]);
                    actions.addLast(new DeleteAction(countToDelete));
                    break;
                case 1:
                    actions.addLast(new ConcatAction(stringItems[1]));
                    break;
            }
        }

        scanner.close();

        while (!actions.isEmpty()) {
            Action action = actions.pollFirst();
            action.act();
        }

        writer.close();
    }

    private static interface Action {
        void act();
    }

    private static class ConcatAction implements Action {

        private final String sToAdd;

        ConcatAction(String sToAdd) {
            this.sToAdd = sToAdd;
        }

        @Override
        public void act() {
            stack.addLast(currentString);
            currentString = currentString.concat(sToAdd);
        }
    }

    private static class PrintAction implements Action {

        private final int position;

        PrintAction(int position) {
            this.position = position;
        }

        @Override
        public void act() {
            try {
                writer.write(currentString.charAt(position - 1));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DeleteAction implements Action {

        private final int count;

        DeleteAction(int count) {
            this.count = count;
        }

        @Override
        public void act() {
            stack.addLast(currentString);
            int length = currentString.length();
            currentString = currentString.substring(0, length - count);
        }
    }

    private static class UndoAction implements Action {

        @Override
        public void act() {
            currentString = stack.pollLast();
        }
    }
}
