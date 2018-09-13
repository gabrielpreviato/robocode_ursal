package URSAL;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

/**
 * 'Megan Fox' é herança da classe AdvancedRobot do Robocode
 * @author Paulo Pacitti
 * @since 2014
 */

public class ForoDeSP extends AdvancedRobot
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
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
			while(true)
			{
				//setTurnRadarRight(360 * direcaoRadar);
				//setAhead(100 *direcaoRun);
				scan();
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
		double radarTurn =
        // Absolute bearing to target
        getHeadingRadians() + e.getBearingRadians()
        // Subtract current radar heading to get turn required
        - getRadarHeadingRadians();

    	setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));

		// double l1, l2, d;
		// double x0, y0, x1, y1;
		// double angleToTurnRadar;
		// double x = getX(), y = getY();
		// double enemyVelocity = e.getVelocity();
		// double enemyHeading = e.getHeading();
		// double distance_to_robot = e.getDistance();
		//
		// x0 = distance_to_robot*Math.cos(getHeading());
		// y0 = distance_to_robot*Math.sin(getHeading());
		// x1 = x0 + enemyVelocity*Math.cos(enemyHeading);
		// y1 = y0 + enemyVelocity*Math.sin(enemyHeading);
		//
		// d = Math.sqrt(Math.pow(x1-x0, 2) + Math.pow(y1-y0, 2));
		// l1 = Math.sqrt(Math.pow(x-x0, 2) + Math.pow(y-y0, 2));
		// l2 = Math.sqrt(Math.pow(x1-x, 2) + Math.pow(y1-y, 2));
		// angleToTurnRadar = Math.acos((l1*l1 + l2*l2 - d*d)/2*l1*l2);
		//
		// System.out.println(angleToTurnRadar);
		//
		// setTurnRadarRight(-180*angleToTurnRadar/Math.PI);
		//
		// execute();
	}

	/**
	 * Método chamado quando MeganFox colide com o robô adversário.
	 * Multiplica a varíavel direcaoRun por -1
	 */

	public void onHitRobot(HitRobotEvent e)
	{
	}

	/**
	 * Método chamado quando MeganFox colide com a parede.
	 * Multiplica a varíavel direcaoRun por -1
	 */
	public void onHitWall(HitWallEvent e)
	{
	}

}



//COPYRIGHT MeganFox by Paulo Pacitti.  (C)2014
