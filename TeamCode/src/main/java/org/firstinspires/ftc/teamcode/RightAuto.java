package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Right-auto", group="auto")
public class RightAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot robot = new Bot(this);

        robot.init(hardwareMap);

        waitForStart();
        //drives forward and hangs the specimen
        robot.encoderDrive(1,19.43);
//        robot.setArmPos(1045);
//        robot.setExtendPos(13.948);
//        robot.setArmPos(450);
//        robot.setExtendPos(0.25);
//        robot.setArmPos(25);


        //pushes samples in
        robot.encoderStrafe(1, -31.8);
        robot.encoderTurn(1,-180);
        robot.encoderDrive(1,-37.5);
        sleep(500);
        robot.encoderStrafe(1, 14.5);
        sleep(500);
        robot.encoderDrive(1,52);
        sleep(100);
        robot.encoderDrive(1,-52.25);
        sleep(100);
        robot.encoderStrafe(1, 18);
        sleep(500);
        robot.encoderDrive(1,50.5);
        robot.encoderDrive(1,-51);
        sleep(500);
        robot.encoderStrafe(1, 17);
        sleep(100);
        robot.encoderDrive(1,51);
        robot.encoderDrive(1,-15);
        sleep(800);
//        robot.runIntakeForTime(1.0, -1);
        robot.encoderDrive(1, 18.5);
//
//
//        //extra specimen
//        robot.setArmPos(230);
//        robot.encoderDrive(1, 20.3);
//        robot.setExtendPos(0.2);
//        robot.setArmPos(315);
//        robot.runIntakeForTime(0.75, 1);
//        robot.setArmPos(580);
//        robot.encoderDrive(1, -6.9);
//        robot.encoderStrafe(1, -70);
//        robot.encoderTurn(1, 185);
//        robot.setArmPos(1070);
//        robot.setExtendPos(12.501);
//        robot.encoderDrive(1,9.6);
//        robot.encoderDrive(1,9.8898);
//        robot.setArmPos(0);
//        robot.setArmPos(250);
//        robot.setExtendPos(0.25);
//        robot.setArmPos(25);
    }
}