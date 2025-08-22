import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:ajith/main.dart';

void main() {
  testWidgets('Login page has email, password and login button', (
    WidgetTester tester,
  ) async {
    // Build the app
    await tester.pumpWidget(const MyApp());

    // Check email field
    expect(find.byType(TextField), findsNWidgets(2)); // email + password

    // Or more specific (if you added hintText)
    expect(find.text('Email'), findsOneWidget);
    expect(find.text('Password'), findsOneWidget);

    // Check Login button
    expect(find.text('Login'), findsOneWidget);

    // Optionally, check if tapping login works (doesn’t crash)
    await tester.tap(find.text('Login'));
    await tester.pump();
  });
}
