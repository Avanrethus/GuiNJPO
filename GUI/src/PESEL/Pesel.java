package PESEL;
public class Pesel {
    private byte PESEL[] = new byte[11];
    private boolean poprawny = false;
    
    public Pesel(String numerPESEL){
        if (numerPESEL.length()!=11){
            poprawny = false;
        }
        else{
            for (int i = 0; i<numerPESEL.length(); i++){
                PESEL[i] = Byte.parseByte(numerPESEL.substring(i,i+1));
            }
            if(spSuma() && spMies() && spDzien()){
                poprawny = true;
            }
            else
                poprawny = false;
        }
    }
    

    public boolean poprawny(){
        return poprawny;
    }
    public int getRok(){
        int rok;
        int mies;
        rok = 10* PESEL[0];
        rok = rok + PESEL[1];
        mies = 10* PESEL[2];
        mies = mies + PESEL[3];
        if(mies > 80 && mies <93){
            rok = rok + 1800;
        }
        else if (mies > 0 && mies < 13){
            rok = rok + 1900;
        }
        else if (mies > 20 && mies < 33){
            rok = rok + 2000;
        } 
        else if (mies > 40 && mies < 53){
            rok = rok + 2100;
        }
        else if (mies > 60 && mies < 73){
            rok = rok + 2200;
        }
        return rok;
    }
    
    public int getMiesac(){
        int mies;
        mies = 10 * PESEL[2];
        mies = mies + PESEL[3];
        if(mies > 80 && mies <93){
            mies = mies - 80;
        }
        else if(mies > 20 && mies < 33){
            mies = mies - 20;
        }
        else if(mies > 40 && mies < 53){
            mies = mies - 40;
        }
        else if(mies > 60 && mies < 73){
            mies = mies - 60;
        }
        return mies;
    }
    public int getDzien(){
        int dzien;
        dzien = 10* PESEL[4];
        dzien = dzien + PESEL[5];
        return dzien;
    }
    public char getPlec(){
        if (poprawny){
            if(PESEL[9] % 2 == 1){
                return 'M';
            }
            else{
                return 'K';
            }
        }
        else 
            return 'N';
    }
    
    public boolean spSuma(){
        int suma = 1* PESEL[0] + 3* PESEL[1] + 7* PESEL[2] + 9* PESEL[3]+
                   1* PESEL[4] + 3* PESEL[5] + 7* PESEL[6] + 9* PESEL[7]+
                   1* PESEL[8] + 3* PESEL[9];
        suma = suma % 10;
        suma = 10 - suma;
        suma = suma % 10;
        if(suma == PESEL[10]){
            return true;
        }
        else
            return false;
    }
    public boolean spMies(){
        int mies = getMiesac();
        if (mies > 0 && mies <13){
            return true;
        }
        else
            return false;
    }
    public boolean rokPrzestepny(int rok){
        if (rok % 4 == 0 && rok % 100 != 0 || rok % 400 == 0){
            return true;
        }
        else 
            return false;
    }
    public boolean spDzien(){
        int rok = getRok();
        int mies = getMiesac();
        int dzien = getDzien();
        if((dzien > 0 && dzien < 32) && 
                (mies == 1 || mies == 3 || mies == 5||
                mies == 7 || mies == 8 || mies == 10 || mies == 12)){
            return true;
        }
        else if((dzien > 0 && dzien < 31) && 
                (mies == 4 || mies == 6 || mies == 9 || mies == 11)){
            return true;
        }
        else if((dzien > 0 && dzien < 30 && rokPrzestepny(rok)) ||
                (dzien > 0 && dzien < 29 && !rokPrzestepny(rok))){
            return true;
        }
        else
            return false;
    }
}
