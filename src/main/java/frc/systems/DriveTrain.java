package frc.systems;

import java.util.logging.Logger;

// import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.lib14.MCR_SRX;

public class DriveTrain {
	private static final Logger logger = Logger.getLogger(DriveTrain.class.getName());
	private static final DriveTrain instance = new DriveTrain();
	private static final ADXRS450_Gyro ADXRS450_GYRO = new ADXRS450_Gyro();

	private static MCR_SRX RightMotor = new MCR_SRX(1);
	private static MCR_SRX LeftMotor = new MCR_SRX(2);

	MasterControls controller = MasterControls.getInstance();


	//private static final Encoder rightEncoder = new Encoder(RobotMap.Drivetrain.RIGHT_ENCODER_1,
	//		RobotMap.Drivetrain.RIGHT_ENCODER_2, false, EncodingType.k4X);
	//private static final Encoder leftEncoder = new Encoder(RobotMap.Drivetrain.LEFT_ENCODER_1,
	//		RobotMap.Drivetrain.LEFT_ENCODER_2, true, EncodingType.k4X);
	private static final DifferentialDrive drive = new DifferentialDrive(LeftMotor, RightMotor);

	private static final ADXRS450_Gyro GYRO = ADXRS450_GYRO;


	private int inverted = 1;

	// Singleton
	protected DriveTrain() {
		//logger.setLevel(RobotMap.LogLevels.driveTrainClass);
	}

	public static DriveTrain getInstance() {
		return instance;
	}

	public void drive() {
		drive.arcadeDrive(controller.forwardSpeed(), controller.direction());
	}

	/**
	 * Used in Autonomous
	 * 
	 * @param speed
	 * @param angle
	 */
	public void arcadeDrive(double speed, double angle) {
		// if only used in autonomous may not need the throttle
		drive.arcadeDrive(speed, angle);
	}

	public void tankDrive() {

	}

	public void devinDrive() {

	}

	public void stop() {
		drive.stopMotor();
	}

	public void calibrateGyro() {
		GYRO.calibrate();
	}

	public void resetGyro() {

	}

	public double getAngle() {
		return GYRO.getAngle();
	}

	/**
	 * Determine the top speed threshold: CRAWL - Lowest speed threshold Normal -
	 * Normal driving conditions SPRINT - Highest speed threshold
	 * 
	 * @link org.usfirst.frc.team4213.robot.RobotMap
	 */
	private double getThrottle() {
		return 0;

	}

	private void invert() {
		inverted = inverted * -1;
	}

	private double getLeftEncoderTics() {
		//return leftEncoder.getDistance();
		return 0;
	}

	private double getRightEncoderTics() {
		//return rightEncoder.getDistance();
		return 0;
	}

	public void printRightEncoder() {
		System.out.println(getRightEncoderTics() + " RightEncoder");
	}

	public void printLeftEncoder() {
		System.out.println(getLeftEncoderTics() + " LeftEncoder");

	}

	public double encoderDifference() {
		return (getRightEncoderTics() - getLeftEncoderTics());
	}

	public double getEncoderTics() {
		//return (getRightEncoderTics() + getLeftEncoderTics()) / 2;
		return getRightEncoderTics();
	}
}