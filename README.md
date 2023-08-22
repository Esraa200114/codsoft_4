## Window Notification App 🔔
### A simple notification window app using Android's Notification system. The app consists of one activity with two buttons, each triggering different types of notifications when clicked.
### ~ Random Fact Notifications: When the first button is clicked, the app generates a notification with a random title and body containing a fact. Each time the button is clicked, a new notification is displayed, providing users with interesting facts.
### ~ Hydration Reminder Notifications: The second button, when clicked, triggers a notification reminding users to stay hydrated. Similar to the random fact notifications, this notification also features a randomly generated title and body to keep the content fresh and engaging.
## Output
https://youtube.com/shorts/Vq6OkuqIic4

## Covered Rules:
### 1. Importing Necessary Libraries for GUI Development: I've made sure to import the essential Android libraries needed for creating a graphical user interface, including android.app.*, android.content.*, android.widget.*, and more.
### 2. Designing the Notification Window with Title, Message, and Image/Icon: I've designed a notification layout using the RemoteViews approach within my getNotificationChannel method. This design includes setting up the title, message, and icon for the notification.
### 3. Creating and Displaying the Notification Window: I've successfully implemented the logic that allows me to create and display notifications using the NotificationCompat.Builder and NotificationManagerCompat classes.
### 4. Setting the Notification Title, Message, and Image/Icon: Within the getNotificationChannel method, I ensure to set the notification's title, message, and image/icon using the RemoteViews customization.
### 5. Enabling User Interaction with the Notification: I've taken care to include interactive features in the notifications. Specifically, I've added action buttons like "View" and "Dismiss," which empower users to engage with the notifications based on their preferences.
### 6. Customizing the Notification's Appearance: To provide a personalized touch, I've meticulously customized the appearance of notification channels using the createNotificationChannels method. Additionally, I've fine-tuned individual notifications by specifying color, priority, and style within the getNotificationChannel method.
### 7. Handling Errors or Exceptions: I've been proactive in dealing with potential errors or exceptions that might arise during notification creation. I've incorporated try-catch blocks to ensure that any issues are managed gracefully.
### 8. Ensuring Compatibility Through Testing: While I haven't explicitly demonstrated it, I've adhered to Android's recommended guidelines, which should ensure that my code works across various Android versions. It's crucial to test the application on diverse devices and Android iterations to guarantee compatibility and a smooth user experience.
