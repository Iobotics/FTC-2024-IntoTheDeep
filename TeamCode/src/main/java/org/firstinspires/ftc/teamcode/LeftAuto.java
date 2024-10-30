package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="Left-auto", group="auto")
public class LeftAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        Bot robot = new Bot(this);

        robot.init(hardwareMap);

        waitForStart();

        //arm position in ticks
        //45 degrees=700
        //0=horizontal
        //extend pos is in inches(2 digits)

        //drives forward and puts the specimen on the hook
        robot.encoderDrive(1,19.4);
        robot.setArmPos(1100);
        robot.setExtendPos(13.0);
        robot.setArmPos(550);
        robot.setExtendPos(0.5);
        robot.setArmPos(50);
        //drives to the samples
        robot.encoderStrafe(1, 32);
        //turns around
        robot.encoderTurn(1,180);
        //back up
        robot.encoderDrive(1,-37);
        robot.encoderStrafe(1, -18.5);
        //sample 1
        robot.encoderDrive(1, 10.5);
        robot.autoIntake(3);
        robot.encoderStrafe(1,3);
        robot.encoderDrive(1,28.5);
        robot.setExtendPos(0.5);
        robot.setArmPos(700);
        robot.encoderTurn(1,45);

        robot.encoderDrive(1,12);

        robot.setArmPos(1450);
        robot.setExtendPos(Bot.MAX_EXT);
        robot.runIntakeForTime(2.5, -1);
        robot.setArmPos(1024);
        robot.setExtendPos(0);
        robot.setArmPos(0);

//        //sample2
//        robot.encoderDrive(1,-48);
//        robot.encoderTurn(1,-12);
//        robot.encoderStrafe(1,-12.2);
//        robot.encoderDrive(1,45);
//        //sample3
//        robot.encoderDrive(1,-45);
//        robot.encoderStrafe(1,-12);
//        robot.encoderDrive(1,43.5);
    }
}