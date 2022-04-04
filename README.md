[![](https://jitpack.io/v/HBiSoft/Fabb.svg)](https://jitpack.io/#HBiSoft/Fabb)

# Fabb

Custom floating action button with animation and the option to add up to 3 action buttons.

![fabbb](https://user-images.githubusercontent.com/35602540/75452245-99d55a80-597a-11ea-91a0-68ba36bcc4d9.png)

Demo:
---

The demo app can be downloaded [here](https://github.com/HBiSoft/Fabb/releases/download/0.0.1/FabbDemo.apk)

![Fbb](https://user-images.githubusercontent.com/35602540/75451010-790c0580-5978-11ea-8ae9-1f8716e2210d.gif)

Implement Fabb into your project:
---

Add JitPack in your root `build.gradle` at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
Add the dependency to your app level `build.gradle`

	dependencies {
	        implementation 'com.github.HBiSoft:Fabb:0.0.1'
	}


Using Fabb library:
---

```java
public class MainActivity extends AppCompatActivity {
    //Declare Fabb
    Fabb fabb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize Fabb
        fabb = findViewById(R.id.fabb);

    }
    
}
```
Set event listeners:
```java
fabb.setEventListener(new FabbListener() {
    @Override
    public void onFabbOpened() {
        //Fabb was opened
    }

    @Override
    public void onFabbClosed() {
        //Fabb was closed
    }

    @Override
    public void onFabbMainPressed() {
        //Main Fabb was pressed
    }

    @Override
    public void onFabbActionOnePressed() {
        //Action button one was pressed
        //This is the action button above the main Fabb
    }

    @Override
    public void onFabbTwoPressed() {
        //Action button two was pressed
    }

    @Override
    public void onFabbThreePressed() {
        //Action button three was pressed
        //This is the action button at the top
    }
});
```

Customisation:
---
This can be done in `xml` or in `java`.

If you set this in java and in xml, then java will be favoured

Java:
```java
// Set number of action buttons (up to 3) - defaults to 0
fabb.setNumberOfActions(3);

// Set custom font
fabb.setCustomFont(Typeface.createFromAsset(getAssets(), "fonts/YourFont.otf"));

// Set main Fabb background
fabb.setMainFabBackgroundColor(getResources().getColor(R.color.colorPrimary));

// Set main Fabb icon
fabb.setMainFabIcon(getResources().getDrawable(R.drawable.ic_mail_white_24dp));

// Set main Fabb color when opened
fabb.setMainFabonOpenedColor(getResources().getColor(R.color.colorPrimary));

// Set action buttons text
fabb.setActionOneText("Email");
fabb.setActionTwoText("Call");
fabb.setActionThreeText("Video Call");

// Set all action buttons background color
fabb.setAllActionsBackground(getResources().getColor(R.color.colorPrimary));

// Set background for spesific action button
fabb.setActionOneBackgroundColor(R.drawable.ic_mail_white_24dp);
fabb.setActionTwoBackgroundColor(R.drawable.ic_mail_white_24dp);
fabb.setActionThreeBackgroundColor(R.drawable.ic_mail_white_24dp);

// Set action buttons icon
fabb.setActionOneIcon(R.drawable.ic_mail_white_24dp);
fabb.setActionTwoIcon(R.drawable.ic_call_white_24dp);
fabb.setActionThreeIcon(R.drawable.fab_cam);

// Keep Fabb open until close button is pressed
fabb.dismissOnOutsideClicked(false);

// Check if Fabb is open (returns boolean)
fabb.isOpen();
```
xml:
```java
// Set number of action buttons (up to 3) - defaults to 0
app:setNumberOfActions="3"

// Set background for spesific action button
app:setActionOneBackgroundColor="#b51a2d"
app:setActionTwoBackgroundColor="#b51a2d"
app:setActionThreeBackgroundColor="#b51a2d"

// Set main Fabb background
app:setMainFabBackgroundColor="#0094de"

// Set main Fabb icon
app:setMainIcon="@drawable/ic_close_black_24dp"

// Set main Fabb color when opened
app:setMainFabOpenedColor="@android:color/white"

// Set action button text
app:setActionOneText="Some Text"
app:setActionTwoText="Some Text"
app:setActionThreeText="Some Text"

// Set action button icon
app:setActionOneIcon="@drawable/ic_close_black_24dp"
app:setActionTwoIcon="@drawable/ic_close_black_24dp"
app:setActionThreeIcon="@drawable/ic_close_black_24dp"
```
