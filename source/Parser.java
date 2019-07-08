import java.lang.*;

public class Parser{
    private String input;
    private int err_flag;
    private int next_index;
    private char lookahead;

    Parser(String in){
        this.input = in;
        this.err_flag = 0;                  //for indicating errors

        this.lookahead = input.charAt(0);
        this.next_index = 1;                //begin from index=0, next=1;
        goal();
    }

    private boolean isDigit(char c){
        if(c >= '0' && c <= '9')
            return true;
        return false;
    }

    public void nextChar(){
        //"consume" next character from input string
        if(this.next_index < input.length()){
            this.lookahead = input.charAt(this.next_index);
            this.next_index++;
            while(" \t\f".contains(String.valueOf(this.lookahead))){        //skip whitespace in input string
                this.lookahead = input.charAt(this.next_index);
                this.next_index++;
            }
        }
        else{
            this.lookahead = '\n';           //declares eof - means that all chars where read+parsed successfully
        }
    }

    public void goal(){
        int result = expr(0);
        if(this.err_flag == 1 || this.lookahead != '\n'){
            System.out.println("Parse Error !\n");
            return;
        }
        System.out.println("Parsing successful. Result = " + result + "\n");
    }

    public int expr(int num){
        //System.out.println("expr, " + num);
        num = term(num);
        num = expr2(num);
        return num;
    }

    public int expr2(int num){
        //System.out.println("expr2, " +  num);
        if(this.lookahead == '^'){
            nextChar();
            int retv = term(num);
            num = num ^ retv;
            num = expr2(num);
        }
        return num;
    }

    public int term(int num){
        //System.out.println("term, " + num);
        num = factor(num);
        num = term2(num);
        return num;
    }

    public int term2(int num){
        //System.out.println("term2, " + num);
        if(this.lookahead == '&'){
            nextChar();
            int retv = factor(num);
            num = retv & num;
            num = term2(num);
        }
        return num;
    }

    public int factor(int num){
        //System.out.println("factor, " + num);
        if(this.lookahead == '\n'){
            this.err_flag = 1;
            return -1;
        }
        if(this.lookahead == '('){
            nextChar();
            num = expr(num);
            if(this.lookahead != ')'){			//check if parenthesis are matched correctly
                this.err_flag = 1;
                return -1;
            }
            nextChar();
            return num;
        }
        else{
            return number(num);
        }
    }

    public int number(int num){
        if(!isDigit(this.lookahead)){         //produce error for inidentified symbol
            this.err_flag = 1;
            return -1;
        }
        num = this.lookahead - '0';           //char to integer
        nextChar();
        return num;
    }
}
