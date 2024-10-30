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

        //drives forward and puts the specimen on the hook
        robot.encoderDrive(1,19.5);
        robot.setArmPos(1100);

        robot.setExtendPos(13.0);
        robot.setArmPos(450);
        robot.setExtendPos(0.5);
        robot.setArmPos(0);
        //puts samples in
        robot.encoderStrafe(1, -32);
        robot.encoderDrive(1,36);
        robot.encoderStrafe(1, -10.5);
        robot.encoderDrive(1,-46.5);
        robot.encoderDrive(1,46.5);
        robot.encoderStrafe(1, -11);
        robot.encoderDrive(1,-46);
        robot.encoderDrive(1,46);
        robot.encoderStrafe(1, -10);
        robot.encoderDrive(1,-46.1);
        robot.setExtendPos(0);
        robot.setArmPos(0);
        robot.encoderStrafe(1, 10);
        robot.encoderTurn(1,180);
        //robot.encoderDrive(1,-25);
        //robot.encoderDrive(1,-45);
    }
}