USE retail_inventory;

INSERT INTO Suppliers (supplier_name, phone, email, address) VALUES
('Northstar Wholesale', '312-555-0101', 'orders@northstar.example', 'Chicago, IL'),
('Lakeview Goods', '312-555-0102', 'sales@lakeview.example', 'Chicago, IL'),
('Midwest Supply Co.', '773-555-0103', 'contact@midwest.example', 'Evanston, IL'),
('Urban Retail Partners', '773-555-0104', 'orders@urbanretail.example', 'Chicago, IL'),
('Prairie Distribution', '847-555-0105', 'sales@prairie.example', 'Schaumburg, IL'),
('Great Lakes Imports', '847-555-0106', 'hello@greatlakes.example', 'Skokie, IL'),
('Metro Office Supply', '708-555-0107', 'orders@metrooffice.example', 'Oak Park, IL'),
('Central Apparel Group', '708-555-0108', 'sales@centralapparel.example', 'Cicero, IL'),
('Windy City Electronics', '630-555-0109', 'orders@windycity.example', 'Naperville, IL'),
('Heritage Home Goods', '630-555-0110', 'sales@heritage.example', 'Aurora, IL');

INSERT INTO Products (supplier_id, product_name, category, price, quantity_in_stock, reorder_level) VALUES
(1, 'Wireless Mouse', 'Electronics', 24.99, 18, 5),
(2, 'Stainless Water Bottle', 'Accessories', 19.50, 4, 8),
(3, 'USB-C Charging Cable', 'Electronics', 12.99, 30, 10),
(4, 'Canvas Tote Bag', 'Apparel', 14.00, 6, 6),
(5, 'Desk Organizer', 'Office', 18.75, 12, 5),
(6, 'LED Desk Lamp', 'Home', 39.99, 3, 5),
(7, 'Hardcover Notebook', 'Office', 9.99, 25, 8),
(8, 'Classic T-Shirt', 'Apparel', 29.00, 9, 10),
(9, 'Bluetooth Speaker', 'Electronics', 49.99, 7, 4),
(10, 'Throw Blanket', 'Home', 34.50, 2, 5);

INSERT INTO Purchase_Orders (supplier_id, order_date, expected_date, status) VALUES
(1, '2026-04-01', '2026-04-08', 'Received'),
(2, '2026-04-02', '2026-04-09', 'Received'),
(3, '2026-04-03', '2026-04-10', 'Ordered'),
(4, '2026-04-04', '2026-04-11', 'Ordered'),
(5, '2026-04-05', '2026-04-12', 'Pending'),
(6, '2026-04-06', '2026-04-13', 'Received'),
(7, '2026-04-07', '2026-04-14', 'Pending'),
(8, '2026-04-08', '2026-04-15', 'Ordered'),
(9, '2026-04-09', '2026-04-16', 'Received'),
(10, '2026-04-10', '2026-04-17', 'Cancelled');

INSERT INTO Order_Items (purchase_order_id, product_id, quantity, unit_cost) VALUES
(1, 1, 20, 14.00),
(2, 2, 15, 10.50),
(3, 3, 40, 6.25),
(4, 4, 20, 7.00),
(5, 5, 15, 9.50),
(6, 6, 10, 22.00),
(7, 7, 30, 4.25),
(8, 8, 25, 15.00),
(9, 9, 12, 30.00),
(10, 10, 10, 18.75);

INSERT INTO Inventory_Transactions (product_id, transaction_type, quantity_change, notes) VALUES
(1, 'Purchase', 20, 'Opening purchase order received'),
(2, 'Sale', -6, 'Retail sales'),
(3, 'Purchase', 40, 'Restock shipment'),
(4, 'Sale', -14, 'Retail sales'),
(5, 'Adjustment', -1, 'Damaged item'),
(6, 'Sale', -7, 'Retail sales'),
(7, 'Purchase', 30, 'Restock shipment'),
(8, 'Sale', -16, 'Retail sales'),
(9, 'Return', 1, 'Customer return'),
(10, 'Sale', -8, 'Retail sales');


