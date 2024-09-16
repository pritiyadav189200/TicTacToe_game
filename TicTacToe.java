package learnFX;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class game extends Application {
	// All gUI variables
	private static Scene scene;
	private GridPane gridPane=new GridPane();
	private BorderPane borderPane=new BorderPane();
	private Label title=new Label("Tic Tac Toe Game");
	private Button restartButton=new Button("Restart Game ");
	Font font=Font.font("Roboto",FontWeight.BOLD,30);
	private Button[] btns=new Button[9];
	// all logic variable
	boolean gameOver=false;
	int activePlayer=0;
	int gameState[]= {3,3,3,3,3,3,3,3,3};
	int winingPosition[][]= {
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}
	};
	
	@Override
	public void start(Stage stage) throws Exception {
		this.createGUI();
		this.handlEvent();
		Scene scene=new Scene(borderPane,550,650);
	    stage.setScene(scene);
		stage.show();		
	}
	
	
// this function is creating GUI
	private void createGUI() {
		
		//creating title
		title.setFont(font);
		restartButton.setFont(font);
		restartButton.setDisable(true);
		//settion title and restart botton
		borderPane.setTop(title);
		borderPane.setBottom(restartButton);
		
		BorderPane.setAlignment(title, Pos.CENTER);
		BorderPane.setAlignment(restartButton,Pos.CENTER);
		borderPane.setPadding(new Insets(20,20,20,20));
		//create button in center
		int label=0;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
			Button	button =new Button();
			button.setId(label+"");
			button.setFont(font);
			button.setPrefHeight(150);
			button.setPrefWidth(150);
			gridPane.setAlignment(Pos.CENTER);
			gridPane.add(button,j, i);
			btns[label]=button;
			label++;

			}
		}
		borderPane.setCenter(gridPane);
	}

	 private void handlEvent()
	 {
		 restartButton.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent actionEvent) {
				//System.out.println("restart button clicked");
				for(int i=0;i<9;i++)
				{
					gameState[i]=3;
					btns[i].setText("");
					gameOver=false;
					restartButton.setDisable(true);
				}
				
			}
		});
		 for( Button btn:btns)
		 {
			 
			 btn.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent actionEvent) {
						
						Button currentBtn=(Button)actionEvent.getSource();
						String ids=currentBtn.getId();
						int idI=Integer.parseInt(ids);
						System.out.println("button clicked on id: "+idI);
						if(gameOver==true)
						{
							Alert alert=new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Error message");
							alert.setContentText("Game over !! try to restart the Game");
							alert.show();
							
						}
						else
						{
							if(gameState[idI]==3)
							{
								if(activePlayer==1) 
								{
								currentBtn.setText("X"+"");
								gameState[idI]=activePlayer;
								checkForWinner();
								activePlayer=0;
								
									
								}
								else
								{
									currentBtn.setText(activePlayer+"");
									gameState[idI]=activePlayer;
									checkForWinner();
									activePlayer=1;
								}
							}
						
							else
							{
								Alert alert=new Alert(Alert.AlertType.ERROR);
								alert.setTitle("Error message");
								alert.setContentText("Place is alread occupied");
								alert.show();
						    }
					     }
						
						}
					
					});
			 
			 }
	 }
	 private void checkForWinner()
	 {
		 //winner check
		 if(!gameOver)
		 {
			for(int wp[]:winingPosition)
			{
				if(gameState[wp[0]]==gameState[wp[1]] && gameState[wp[1]]==gameState[wp[2]] && gameState[wp[0]]!=3)
				{
					Alert alert=new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Success message");
					alert.setContentText(activePlayer+ " has won the game");
					alert.show();
					gameOver=true;
					restartButton.setDisable(false);
					break;
				}
			}
			 
		 }
		 
	 }


	public static void main(String[] args) {
		launch(args);
	}

	

}
