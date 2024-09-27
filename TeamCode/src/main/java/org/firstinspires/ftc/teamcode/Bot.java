package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Bot {

    //OpMode Declaration
    private LinearOpMode opMode;

    //Motor Declaration
    private DcMotor leftMotorFront;
    private DcMotor rightMotorFront;
    private DcMotor leftMotorBack;
    private DcMotor rightMotorBack;

    //Statistics for measurements
    private static final double WHEEL_DIAMETER_INCHES = 1; // For circumference / distance measurements
    private static final int TICKS_PER_REV = 1440;
    private static final double ARM_GEAR_RATIO = 1.0;
    private static final double DISTANCE_PER_REV = 10.0;

    //Extending and pivot motor
    private DcMotor extendArmMotor;
    private DcMotor armPivotMotor;

    private ElapsedTime runtime = new ElapsedTime();

    /**
     * Constructor for Bot object
     * @param opMode
     */
    public Bot(LinearOpMode opMode) {
        this.opMode = opMode;
        init(opMode.hardwareMap);
    }

    /**
     * Initialize hardware mapping for robot
     * @param map
     */
    public void init(HardwareMap map){

        //Connecting declared motors to classes of DcMotors and respected names
        leftMotorFront = map.get(DcMotor.class, "left_front");
        leftMotorBack = map.get(DcMotor.class, "left_back");
        rightMotorFront = map.get(DcMotor.class, "right_front");
        rightMotorBack = map.get(DcMotor.class, "right_back");

        extendArmMotor = map.get(DcMotor.class, "extend_arm");
        armPivotMotor = map.get(DcMotor.class, "pivot_arm");

        //Set RunModes for Encoder Usage
        /*
        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
         */

        //Set Direction of each Motors
        // switch REVERSE and FORWARD if controls are opposite
        // This is set for Mechanum drive
        leftMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);

        extendArmMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        armPivotMotor.setDirection(DcMotorSimple.Direction.FORWARD);

    }

    /**
     * Set drive train power for mechanum drive
     * @param frontLeftPower
     * @param backLeftPower
     * @param frontRightPower
     * @param backRightPower
     */
    public void setDriveTrain(
            double frontLeftPower, double backLeftPower,
            double frontRightPower, double backRightPower
    ) {
        leftMotorFront.setPower(frontLeftPower);
        leftMotorBack.setPower(backLeftPower);
        rightMotorFront.setPower(frontRightPower);
        rightMotorBack.setPower(backRightPower);
    }


    public void setExtendPower(double power){
        extendArmMotor.setPower(power);
    }

    public double getArmPosition(){
        int currentTicks = extendArmMotor.getCurrentPosition();

        double revolutions = (double) currentTicks / TICKS_PER_REV;

        return revolutions * ARM_GEAR_RATIO * DISTANCE_PER_REV;
    }

    public void autoPivotArm(
            int targetPosition, double power
    ) {
        armPivotMotor.setTargetPosition(targetPosition);
        armPivotMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armPivotMotor.setPower(power);
    }

    public void setPivotPower(double power){
        armPivotMotor.setPower(power);
    }


}