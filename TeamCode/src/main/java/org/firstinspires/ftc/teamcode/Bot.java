package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Bot {

    //OpMode Declaration
    private LinearOpMode opMode;

    //Drive Motor Declaration
    private DcMotor leftMotorFront = null;
    private DcMotor rightMotorFront = null;
    private DcMotor leftMotorBack = null;
    private DcMotor rightMotorBack = null;

    //Subsystem Declaration
    private Servo leftExtend = null;
    private Servo rightExtend = null;

    private DcMotor rightLift = null;

    private CRServo intake = null;

    // Hardware Map Declaration
    private HardwareMap hwMap = null;

    //Statistics for measurements
    public static final int MAX_EXT = -2648;
    public static final double MIN_EXTEND = 0;

    public static final int LEFT_LIFT_MAX = 7255;
    public static final int LEFT_LIFT_MIN = -101;
    public static final int RIGHT_LIFT_MAX = 7302;
    public static final int RIGHT_LIFT_MIN = -10;
    public static final int MAX_PIVOT = 2560;
    public static final int MIN_PIVOT = -180;


    //Drive Encoder Stats
    private static final double COUNTS_PER_MOTOR_REV = 537.7;    // GoBilda 5203 motor encoder res
    private static final double DRIVE_GEAR_REDUCTION = 1.0;     // 1:1
    private static final double WHEEL_DIAMETER_INCHES = 4.09449;     // For figuring circumference
    private static final double CIRCUMFERENCE = (WHEEL_DIAMETER_INCHES * Math.PI);
    private static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / CIRCUMFERENCE;
    private static final double DISTANCE_PER_ENCODER =  CIRCUMFERENCE/COUNTS_PER_MOTOR_REV;


    private static final double MAX_DISTANCE = 25.5;
    private static final double TICKS_PER_INCH_EXT = MAX_EXT / MAX_DISTANCE;

    private static final int BUFFER = 10;
    
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

        hwMap = map;

        //Connecting declared motors to classes of DcMotors and respected names
//        leftMotorFront = hwMap.get(DcMotor.class, "left_front");
//        leftMotorBack = hwMap.get(DcMotor.class, "left_back");
//        rightMotorFront = hwMap.get(DcMotor.class, "right_front");
//        rightMotorBack = hwMap.get(DcMotor.class, "right_back");
//
//
//        leftMotorFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightMotorFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftMotorBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        rightMotorBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightLift = hwMap.get(DcMotor.class, "right_lift");

        rightLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLift.setDirection(DcMotorSimple.Direction.FORWARD);


        leftExtend = hwMap.get(Servo.class, "left_extend");
        rightExtend = hwMap.get(Servo.class, "right_extend");

        rightExtend.setDirection(Servo.Direction.REVERSE);

        intake = hwMap.get(CRServo.class, "intake");
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        //set encoders to 0 on init
//        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//        //Set RunModes for Encoder Usage
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        //Set Direction of each Motors
        // switch REVERSE and FORWARD if controls are opposite
//        leftMotorFront.setDirection(DcMotorSimple.Direction.REVERSE);
//        leftMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightMotorFront.setDirection(DcMotorSimple.Direction.FORWARD);
//        rightMotorBack.setDirection(DcMotorSimple.Direction.FORWARD);

    }

//    /**
//     * Set drive train power for mechanum drive
//     * @param frontLeftPower front left motor power
//     * @param backLeftPower back left motor power
//     * @param frontRightPower front right motor power
//     * @param backRightPower back right motor power
//     */
//    public void setDriveTrain(
//           double frontLeftPower, double backLeftPower,
//           double frontRightPower, double backRightPower
//    ) {
//       leftMotorFront.setPower(frontLeftPower);
//       leftMotorBack.setPower(backLeftPower);
//       rightMotorFront.setPower(frontRightPower);
//       rightMotorBack.setPower(backRightPower);
//    }
//
//    /**
//     * Drive using encoders for auto
//     * @param speed speed for power
//     * @param distance distance to travel
//     */
//    public void encoderDrive(double speed, double distance) {
//        int newfrontLeftTarget;
//        int newfrontRightTarget;
//        int newbackLeftTarget;
//        int newbackRightTarget;
//
//        //calculate distance to encoder ticks
//        newfrontLeftTarget = leftMotorFront.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//        newfrontRightTarget = rightMotorFront.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//        newbackLeftTarget = leftMotorBack.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//        newbackRightTarget = rightMotorBack.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//
//        // set target position to hit for distance given
//        leftMotorFront.setTargetPosition(newfrontLeftTarget);
//        rightMotorFront.setTargetPosition(newfrontRightTarget);
//        leftMotorBack.setTargetPosition(newbackLeftTarget);
//        rightMotorBack.setTargetPosition(newbackRightTarget);
//
//        // Turn On RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        // start motion
//        leftMotorFront.setPower(Math.abs(speed));
//        rightMotorFront.setPower(Math.abs(speed));
//        leftMotorBack.setPower(Math.abs(speed));
//        rightMotorBack.setPower(Math.abs(speed));
//
//        // Telemetry
//        while (opMode.opModeIsActive() &&
//                (leftMotorFront.isBusy() && rightMotorFront.isBusy() && leftMotorBack.isBusy() && rightMotorBack.isBusy())) {
//            opMode.telemetry.addData("frontLeftEncoder", leftMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("frontRightEncoder", rightMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("backLeftEncoder", leftMotorBack.getCurrentPosition());
//            opMode.telemetry.addData("backRightEncoder", rightMotorBack.getCurrentPosition());
//            opMode.telemetry.update();
//        }
//
//
//        // Stop all motion;
//        leftMotorFront.setPower(0);
//        rightMotorFront.setPower(0);
//        leftMotorBack.setPower(0);
//        rightMotorBack.setPower(0);
//
//        // Turn off RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // set encoders to 0 when function is completed
//        // we do this to ensure the next set of functions are based at 0 instead of taking
//        // the encoder value the function finishes at
//        resetEncoder();
//    }
//
//    /**
//     * Turn using encoders for auto (pivot)
//     * @param speed speed for power
//     * @param degrees angle degree to turn
//     */
//    public void encoderTurn(double speed, double degrees) {
//
//        //calculate target count based on degrees to turn
//        double turnCircumference = Math.PI * (degrees * 4 / 360) * (WHEEL_DIAMETER_INCHES * 2);
//        int targetCounts = (int) (turnCircumference / DISTANCE_PER_ENCODER);
//
//        // set target position of encoders based on degree to turn
//        leftMotorFront.setTargetPosition(-targetCounts);
//        rightMotorFront.setTargetPosition(targetCounts);
//        leftMotorBack.setTargetPosition(-targetCounts);
//        rightMotorBack.setTargetPosition(targetCounts);
//
//        // Turn On RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        // reset the timeout time and start motion.
//        leftMotorFront.setPower(Math.abs(speed));
//        rightMotorFront.setPower(Math.abs(speed));
//        leftMotorBack.setPower(Math.abs(speed));
//        rightMotorBack.setPower(Math.abs(speed));
//
//        while (opMode.opModeIsActive() &&
//                (leftMotorFront.isBusy() && leftMotorBack.isBusy() && rightMotorFront.isBusy() && rightMotorBack.isBusy())) {
//            opMode.telemetry.addData("frontLeftEncoder", leftMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("frontRightEncoder", rightMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("backLeftEncoder", leftMotorBack.getCurrentPosition());
//            opMode.telemetry.addData("backRightEncoder", rightMotorBack.getCurrentPosition());
//            opMode.telemetry.update();
//        }
//
//        // Stop all motion;
//        leftMotorFront.setPower(0);
//        rightMotorFront.setPower(0);
//        leftMotorBack.setPower(0);
//        rightMotorBack.setPower(0);
//
//        // Turn off RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // set encoders to 0 when function is completed
//        // we do this to ensure the next set of functions are based at 0 instead of taking
//        // the encoder value the function finishes at
//        resetEncoder();
//    }
//
//    /**
//     * Strafe using encoders for auto
//     * NOTE: Pos distance is left strafe, Neg distance is right strafe
//     * @param speed speed of motors
//     * @param distance distance to travel
//     */
//    public void encoderStrafe(double speed, double distance) {
//        int newfrontLeftTarget;
//        int newfrontRightTarget;
//        int newbackLeftTarget;
//        int newbackRightTarget;
//
//        // Determine new target position, and pass to motor controller
//        newfrontLeftTarget = leftMotorFront.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH);
//        newfrontRightTarget = rightMotorFront.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//        newbackLeftTarget = leftMotorBack.getCurrentPosition() + (int) (-distance * COUNTS_PER_INCH);
//        newbackRightTarget = rightMotorBack.getCurrentPosition() + (int) (distance * COUNTS_PER_INCH);
//
//        //Set target position for motors
//        leftMotorFront.setTargetPosition(newfrontLeftTarget);
//        rightMotorFront.setTargetPosition(newfrontRightTarget);
//        leftMotorBack.setTargetPosition(newbackLeftTarget);
//        rightMotorBack.setTargetPosition(newbackRightTarget);
//
//        // Turn On RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
//
//        // reset the timeout time and start motion.
//        leftMotorFront.setPower(Math.abs(speed));
//        rightMotorFront.setPower(-Math.abs(speed));
//        leftMotorBack.setPower(-Math.abs(speed));
//        rightMotorBack.setPower(Math.abs(speed));
//
//        //Telemetry
//        while (opMode.opModeIsActive() &&
//                (leftMotorFront.isBusy() && leftMotorBack.isBusy() && rightMotorFront.isBusy() && rightMotorBack.isBusy())) {
//            opMode.telemetry.addData("frontLeftEncoder", leftMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("frontRightEncoder", rightMotorFront.getCurrentPosition());
//            opMode.telemetry.addData("backLeftEncoder", leftMotorBack.getCurrentPosition());
//            opMode.telemetry.addData("backRightEncoder", rightMotorBack.getCurrentPosition());
//            opMode.telemetry.update();
//            }
//
//        // Stop all motion;
//        leftMotorFront.setPower(0);
//        rightMotorFront.setPower(0);
//        leftMotorBack.setPower(0);
//        rightMotorBack.setPower(0);
//
//        // Turn off RUN_TO_POSITION
//        leftMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        leftMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
//
//        // set encoders to 0 when function is completed
//        // we do this to ensure the next set of functions are based at 0 instead of taking
//        // the encoder value the function finishes at
//        resetEncoder();
//    }
//
//    /**
//     * Sets encoder values of drive motors to 0
//     */
//    private void resetEncoder(){
//        leftMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        leftMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//        rightMotorFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
//
//    }

    // === EXTEND FUNCTIONS ===
    public double getLServoPos(){return leftExtend.getPosition();}

    public double getRServoPos(){return rightExtend.getPosition();}

    public void setServoPos(double tick){
        this.leftExtend.setPosition(tick);
        this.rightExtend.setPosition(tick);
    }

    // === LIFT FUNCTIONS ==
    public int getRLiftPos(){return rightLift.getCurrentPosition();}

    public void setLift(int tick){
        this.rightLift.setTargetPosition(tick);
        this.rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rightLift.setPower(1.0);

        while(opMode.opModeIsActive() && this.rightLift.isBusy()){
            opMode.telemetry.addData("right lift pos: ", this.rightLift.getCurrentPosition());
            opMode.telemetry.update();
        }
        this.rightLift.setPower(0);
        this.rightLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    // === INTAKE FUNCTIONS ===
    public void runIntake(){ this.intake.setPower(1.0);}

    public void runOuttake(){ this.intake.setPower(-1.0);}

    public void stopIntake() { this.intake.setPower(0.0);}


}
