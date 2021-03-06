#include <ComputerSerial.h>
#include <LiquidCrystal.h>

//Constants
static const int PIN_LED = 5;
static const int PIN_SOUND = 6;
static const int PIN_VIBRATION = 4;
static const int PIN_DEBUG_LED = 13;

static const int PIN_SCREEN_A = 8;
static const int PIN_SCREEN_B = 7;
static const int PIN_SCREEN_1 = 12;
static const int PIN_SCREEN_2 = 11;
static const int PIN_SCREEN_3 = 10;
static const int PIN_SCREEN_4 = 9;

//Global variables
static ComputerSerial comp;
static LiquidCrystal lcd(PIN_SCREEN_A, PIN_SCREEN_B, PIN_SCREEN_1, PIN_SCREEN_2, PIN_SCREEN_3, PIN_SCREEN_4);

void* text(byte flag, byte content[], word contentSize)
{
  boolean wrap = false;
  
  lcd.clear();
  lcd.home();
  for(int i = 0; i < contentSize; i++) 
  {
    char letter = (char) content[i];
    
    //newline?
    if(letter == '\n')
    {
      wrap = true;
      lcd.setCursor(0, 1);
      continue;
    }
    
    //Print character
    lcd.print(letter);
    
    //Wrap to next line if needed
    if(!wrap && i >= 15)
    {
      wrap = true;
      lcd.setCursor(0, 1);
    }
    
    //Max 30 letters on the display
    if(i > 30) break;
  }
  
}

void* data(byte flag, byte data[], word dataSize)
{
    tone (PIN_SOUND, 200);
    delay (100);
    tone (PIN_SOUND, 600);
    delay (100);
    tone (PIN_SOUND, 1350);
    delay (200);
    tone (PIN_SOUND, 200);
    delay (100);

  noTone(PIN_SOUND);
}

void setup()
{
    //Initialize computer serial class
    comp.begin(9600);   
    comp.setDeviceName("Jacket Prototype");
    comp.setDeviceVersion("2.0.3");
    
    //Setup pins
    comp.addDeviceService("VIBRATION", "4");
    pinMode(PIN_VIBRATION, OUTPUT);

    comp.addDeviceService("SPEAKER", "6");
    comp.attachFunction(comp.OPCODE_DATA, &data);
    pinMode(PIN_SOUND, OUTPUT);	
    
    comp.addDeviceService("LCD_SCREEN", "");
    comp.attachFunction(comp.OPCODE_TEXT, &text);
    lcd.begin(16, 2);
    lcd.setCursor(0, 0);
    lcd.print("OSNAP Jacket");
    lcd.setCursor(0, 1);
    lcd.print("Scan QR tag");
    
    comp.addDeviceService("LED_LAMP", "5,13");
    pinMode(PIN_LED, OUTPUT);
    pinMode(PIN_DEBUG_LED, OUTPUT);
    
    //Download links
    comp.addDeviceDownloadLink("http://folk.ntnu.no/svarvaa/utils/pro2www/#appId1", "OSNAP Jacket");
}

void loop()
{

}

void serialEvent()
{
    comp.serialEvent();
}
