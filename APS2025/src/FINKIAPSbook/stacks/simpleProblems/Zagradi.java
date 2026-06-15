package FINKIAPSbook.stacks.simpleProblems;

import FINKIAPSbook.stacks.ArrayStack;

public class Zagradi {
    public static boolean daliKorektni(String phrase) {
        ArrayStack<Character> bracketStack = new ArrayStack<Character>(100);

        for (int i = 0; i < phrase.length(); i++){
            char cur = phrase.charAt(i);
            if (cur == '(' || cur == '[' || cur == '{')
                bracketStack.push(cur);

            else if (cur == ')' || cur == ']' || cur == '}') {
                if (bracketStack.isEmpty()) return false;

                char left = bracketStack.pop();
                if (!daliSoodvetni(left,cur)) return false;
            }
        }
        return (bracketStack.isEmpty());
    }

    public static boolean daliSoodvetni(char left, char right) {
        switch (left) {
            case '(':
                return (right == ')');
            case '[':
                return (right == ']');
            case '{':
                return (right == '}');
        }
        return false;
    }
    public static void main(String[] args) {
        String phrase = " s x (s-a) x (s-b) x (s-c)";
        System.out.println(phrase + " ima " + (daliKorektni(phrase) ? "korektni" : "nekorektni") + " zagradi. ");
    }
}
