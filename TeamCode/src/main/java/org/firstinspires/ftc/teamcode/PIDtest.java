//private final double ticksPerRevolution = 1000;  // Get for your motor and gearing.
//private double prevTime;  // The last time loop() was called.
//private int prevLeftEncoderPosition;   // Encoder tick at last call to loop().
//private int prevRightEncoderPosition;  // Encoder tick at last call to loop().
//
//public void init() {
//        // ... other code
//        prevTime = 0;
//        prevLeftEncoderPosition = leftFrontMotor.getCurrentPosition();
//        prevRightEncoderPosition = RightFrontMotor.getCurrentPosition();
//        }
//
//public void loop() {
//        // ... other code
//
//        // Compute speed of left,right motors.
//        double deltaTime = time - prevTime;
//        double leftSpeed = (leftFrontMotor.getCurrentPosition() - prevLeftEncoderPosition) /
//        deltaTime;
//        double rightSpeed = (rightFrontMotor.getCurrentPosition() - prevRightEncoderPosition) /
//        deltaTime;
//        // Track last loop() values.
//        prevTime = time;
//        prevLeftEncoderPosition = leftFrontMotor.getCurrentPosition();
//        prevRightEncoderPosition = rightFrontMotor.getCurrentPosition();
//
//        // ... other code
//        }