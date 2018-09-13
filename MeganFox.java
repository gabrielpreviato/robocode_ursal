package setor7;
import robocode.*;
import java.awt.Color;

/**
 * 'Megan Fox' é herança da classe AdvancedRobot do Robocode
 * @author Paulo Pacitti
 * @since 2014
 */ 

public class MeganFox extends AdvancedRobot
{
	/**
	 * Variável de valores ou 1 ou -1 
	 * que orienta a direção do movimento do robô
	 */
		protected byte direcaoRun = 1;
	
	/**
	 * Variável de valores 1 ou - 1
	 * que orienta a direção do radar
	 */
		protected byte direcaoRadar = 1;
		

	/**
	 * Método de loop principal do robô
	 * <br> <br>
	 * 
	 * Gera a inicialização: <br>
	 * 	. Aplica as cores<br>
	 * 	. Ajusta o radar para ficar na mesma direção se o veículo ou o canhão girarem <br>
	 * <br>
	 * Gera o loop principal: <br>
	 *  . Gira o radar 360 *direcaoRadar <br>
	 * 	. Movimenta o robô 100 *direcaoRun 	
	 */
		public void run() 
		{

			setBodyColor(new Color(231, 76, 60));
			setGunColor(new Color (52, 73, 94));
			setRadarColor(new Color(236, 240, 241));
			setBulletColor(new Color(46, 204, 113));

			setAdjustRadarForRobotTurn(true);
			
		// loop principal
			while(true) 
			{						
				setTurnRadarRight(360 * direcaoRadar);
				setAhead(100 *direcaoRun);
				execute();
			} 
		}


	/**
	 *  Função que normaliza os ângulos para +180 ou -180
	 * @param angle obtido do e.getBearing()
	 * @return o ângulo normalizado
	 */
	public double normalizarBearing(double angle) 
	{
		while (angle >  180) angle -= 360;
		while (angle < -180) angle += 360;
		return angle;
	}


	/**
	 * Método chamado quando o radar escaneia o robô
	 * <br><br>
	 * - Multiplica direcaoRadar por -1
	 * <br><br>
	 * Duas condições: <br>
	 * 	. 	Se o valor da distância do robô adversário for maior que 200 e maior que 75 <br>
	 * 	 MeganFox irá perseguir o inimigo.<br>
	 * 	.	Senão, MeganFox posicionar 90º do adversário	  
	 */
	public void onScannedRobot(ScannedRobotEvent e) 
	{	

		 // Altera o valor da variável de direção do radar
		 
		direcaoRadar  *= -1;
		

		  //Se  a distância for entre 75 e 200, persegue o inimigo
		if (e.getDistance() > 200 && e.getDistance() > 75)
		{
			
			 // Gira o robô para o ângulo que se encontra o adversário 
			setTurnRight(e.getBearing());
			setAhead(50);
			
			
			//  Realiza a fórmula para girar o radar no ângulo do inimigo  
			setTurnRadarRight(getHeading() - getRadarHeading() + normalizarBearing(e.getBearing()));
			
		  	 // Realiza a fórmula para girar a arma no ângulo do inimigo
			setTurnGunRight(getHeading() - getGunHeading() + normalizarBearing(e.getBearing()));

			 // Se a distância for suficiente realiza a fórmula de força do tiro 
			if (e.getDistance() < 375)
			{
				setFire(Math.min(400 / e.getDistance(), 3));
			} 
				
			execute();
		}
		

		// Senão posiciona-se 90º a posição do inimigo 
		else
		{
		    // Gira o robô para o ângulo que se encontra o adversário + 90º
			setTurnRight(e.getBearing() + 90);
			setAhead(50);
			
			// Realiza a fórmula para girar o radar no ângulo do inimigo  
			setTurnRadarRight(getHeading() - getRadarHeading() + normalizarBearing(e.getBearing()));
				
		   // Realiza a fórmula para girar a arma no ângulo do inimigo 
			setTurnGunRight(getHeading() - getGunHeading() + normalizarBearing(e.getBearing()));

		  // Se a distância for suficiente realiza a fórmula de força do tiro 
			if (e.getDistance() < 100)
			{
				setFire(Math.min(400 / e.getDistance(), 3));
			} 
		
			execute();
		}	
	}

	/**
	 * Método chamado quando MeganFox colide com o robô adversário.
	 * Multiplica a varíavel direcaoRun por -1 
	 */
	
	public void onHitRobot(HitRobotEvent e) 
	{
		direcaoRun *= -1;
	}
	
	/**
	 * Método chamado quando MeganFox colide com a parede.
	 * Multiplica a varíavel direcaoRun por -1 
	 */
	public void onHitWall(HitWallEvent e) 
	{	
		direcaoRun *= -1;
	}
	
}
	


//COPYRIGHT MeganFox by Paulo Pacitti.  (C)2014							