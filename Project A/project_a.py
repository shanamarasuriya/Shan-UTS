class MenuItem:
    def __init__(self, name, price, preparation_time):
        self.name = name
        self.price = price
        self.preparation_time = preparation_time
        self.quantity = 0

    def __str__(self):
        return f"{self.name} - ${self.price}"

    def order(self, quantity):
        self.quantity = quantity

    def calculate_total(self):
        return self.quantity * self.price

    def display_order(self):
        print(f"Order Details for {self.name}:")
        print(f"Quantity: {self.quantity}")
        print(f"Total Price: ${self.calculate_total()}")


class OrderSystem:
    def __init__(self):
        self.menu = [
            MenuItem("Cheeseburger", 5.99, 2),
            MenuItem("Veggie Burger", 4.99, 3),
            MenuItem("Chicken Burger", 6.99, 4),
            MenuItem("Crispy Chicken Burger", 7.99, 4),
        ]

    def display_menu(self):
        print("Welcome to Waruna Shan's Burger Hut! Menu:")
        for i in range(len(self.menu)):
            item = self.menu[i]
            print(f"{i + 1}. {item}")

    def take_order(self):
        print("Place your order:")
        for item in self.menu:
            quantity = int(input(f"How many {item.name}s would you like to order? (Enter 0 if none): "))
            item.order(quantity)

    def calculate_total_time(self):
        total_time = sum(item.quantity * item.preparation_time for item in self.menu)
        return total_time

    def display_bill_and_time(self):
        total_time = self.calculate_total_time()
        print("******************************************")
        print("\nOrder Summary:")
        print("------------------------------------------")
        print("------------------------------------------")
        for item in self.menu:
            if item.quantity > 0:
                item.display_order()
        print("------------------------------------------")
        print("------------------------------------------")
        print(f"\nTotal Preparation Time: {total_time} minutes")
        print("------------------------------------------")
        print("\nThank you for choosing Waruna Shan's Burgers!")
        print("Enjoy your food!")
        print("******************************************")
        print("******************************************")
        print("------------------------------------------")


class SoftDrinkMenu:
    def __init__(self):
        self.soft_drinks = [
            MenuItem("Soft Drink - Coke", 1.99, 0),
            MenuItem("Soft Drink - Pepsi", 1.99, 0),
            MenuItem("Soft Drink - Sprite", 1.99, 0),
            MenuItem("Soft Drink - Fanta", 1.99, 0),
        ]

    def display_soft_drinks(self):
        print("\nOrder for Soft Drinks:")
        print("------------------------------------------")
        print("Please select your desired soft drink from the list below:")
        for i in range(len(self.soft_drinks)):
            drink = self.soft_drinks[i]
            print(f"{i + 1}. {drink}")

    def take_soft_drink_order(self):
        choice = int(input("Enter the number of your choice: "))
        if 1 <= choice <= len(self.soft_drinks):
            quantity = int(input(f"How many {self.soft_drinks[choice - 1].name}s would you like to order? (Enter 0 if none): "))
            self.soft_drinks[choice - 1].order(quantity)
            if self.soft_drinks[choice - 1].quantity > 0:
                self.soft_drinks[choice - 1].display_order()

        while True:
            buy_another = input("Do you want to buy another soft drink? (yes/no): ").lower()
            if buy_another == "yes":
                choice = int(input("Enter the number of your choice: "))
                if 1 <= choice <= len(self.soft_drinks):
                    quantity = int(input(f"How many {self.soft_drinks[choice - 1].name}s would you like to order? (Enter 0 if none): "))
                    self.soft_drinks[choice - 1].order(quantity)
                    if self.soft_drinks[choice - 1].quantity > 0:
                        self.soft_drinks[choice - 1].display_order()
                else:
                    print("Invalid choice. Please try again.")
                    continue
            elif buy_another == "no":
                break
            else:
                print("Invalid input. Please enter 'yes' or 'no'.")
                continue

    def display_soft_drink_order(self):
        print("------------------------------------------")
        print("******************************************")

#scenerio
if __name__ == "__main__":
    order_system = OrderSystem()
    order_system.display_menu()
    order_system.take_order()

    soft_drink_menu = SoftDrinkMenu()
    soft_drink_menu.display_soft_drinks()
    soft_drink_menu.take_soft_drink_order()

    soft_drink_menu.display_soft_drink_order()
    order_system.display_bill_and_time()
