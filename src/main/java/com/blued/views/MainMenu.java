package com.blued.views;

import com.blued.util.ScannerUtil;

public class MainMenu implements View {

	public void printMenu() {
		System.out.println("WELCOME TO GOLD ROGER'S DINGHY OF DEBLOON DEPOSITS AND WITHDRAWALS AND TRANSFERS!");
		System.out.println("____________________________________________________________________________________");
		System.out.println("1. Take a gander at ye booty! (Login)");
		System.out.println("2. Start your own booty chest! (Create Account)");
		System.out.println("0. Quit!");
	}
	
	public View process() {
		printBoat();
		printMenu();
		int selection = ScannerUtil.getInput(2);
		
		switch(selection) {
			case 0: return null;
			case 1: return new LoginMenu();
			case 2: return new CreateAccountMenu();
			default: return null;
		}
	}
	
	public void printBoat() {
		System.out.println("                                      I");
		System.out.println("                                   _.$I");
		System.out.println("                                _.$#$$I");
		System.out.println("                   I            $._   I");
		System.out.println("                   I            _.$   I");
		System.out.println("                   I     ...:::\"\"\"    I");
		System.out.println("                   I                  IU");
		System.out.println("                   I                  ==");
		System.out.println("                   IU                 IU");
		System.out.println("                   ==           =======U=======");
		System.out.println("                   IU           |      U      |");
		System.out.println("               =====U=====      |      U      |");
		System.out.println("               |    U    |     |       U       |");
		System.out.println("               |    U    |     |       U       |");
		System.out.println("              |     U     |   |        U        |");
		System.out.println("              |     U     |   |        U        |");
		System.out.println("             |      U      |  |        U        |         I");
		System.out.println("             |      U      | |         U         |    ---~I        //");
		System.out.println("            |       U       ||         U         | -=~ qp I       //|");
		System.out.println("            |       U       |         _U____      | }  >< I      // |");
		System.out.println("           |       _U___    |___----~~\\WWWW/~---__|/  ---~I     //  |");
		System.out.println("           |__---~~YYYYY---__|         U||         ~~~    I    //   |");
		System.out.println("                    U||    =============||============    I|| //    `.");
		System.out.println("           ==========||====|            ||           |    ===//      |");
		System.out.println("           |         ||    |            ||           |    I||/       |");
		System.out.println("           |         ||    |            ZZ           |    /||        `.");
		System.out.println("           |         ZZ    |            ZZ           |   //||         |");
		System.out.println("           |         ZZ    |            ||           |  // ||         |");
		System.out.println("    I      |         ||    |            ||           | //  ||         `.");
		System.out.println(" ===I===   |         ||    |            ||           |//   ||          |");
		System.out.println(" |  I  |   |         ||    |            ||           //    ||          |");
		System.out.println(" |  I  |   |         ||    |            ZZ          //_____||_-----~~~~~\\");
		System.out.println(" |__I__|   |_________||____|            ZZ          /|     ||     !!!!!! \\");
		System.out.println("   .I                ||    |            ZZ           |     ||     ;  A I==+==");
		System.out.println("   `bo.              ||    |____________||___________|   !!!!!!!!!    /");
		System.out.println("   ===`bo.===        ||                 ||               ;   888    ,/");
		System.out.println("   |     `boo.   TTTTTTTTT              ||   !!!!!!!!!!!!   A   A A I");
		System.out.println("   |     &--`boo/        |______________LL   ;                 iiiiiii");
		System.out.println("   |     (___        8888 !!!!!!!!!!!!!!!!---'8888888            /");
		System.out.println("   |________\\                                                   /");
		System.out.println("             \\            []   []   []   []   []   []   []     /");
		System.out.println("              \\                                               =|\\");
		System.out.println("               \\                                              =||");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}

}
