import 'package:flutter_test/flutter_test.dart';
import 'package:ajith/main.dart';

void main() {
  testWidgets('HomePage has AppBar title and body text', (
    WidgetTester tester,
  ) async {
    // Build the app
    await tester.pumpWidget(const MyApp());

    // ✅ AppBar title check
    expect(find.text('Aboss'), findsOneWidget);

    // ✅ Body text check
    expect(find.text('Hi Antony'), findsOneWidget);
  });
}
