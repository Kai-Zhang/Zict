package client;

public class DealAnswer {
	public void deal(String content){
		if (content.contains("Register")){
			if (content.equals("Register Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		if (content.contains("Login")){
			if (content.equals("Login Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		if (content.contains("Zan")){
			if (content.equals("Zan Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		if (content.contains("Cancel")){
			if (content.equals("Cancel Success!")){
				//Msg Box
			}
			else{
				//Msg Box
			}
		}
		if (content.equals("No such words!")){
			
		}
		else{
			//Set Explaination
		}
	}
}
