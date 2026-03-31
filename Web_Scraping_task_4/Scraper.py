import requests
from bs4 import BeautifulSoup
import csv

url = "http://books.toscrape.com/"
response = requests.get(url)

soup = BeautifulSoup(response.text, 'html.parser')

products = soup.find_all('article', class_='product_pod')

# Open CSV file
with open('products.csv', 'w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)

    # Header
    writer.writerow(['Product Name', 'Price', 'Rating'])

    # Loop through products
    for product in products:
        name = product.h3.a['title']
        price = product.find('p', class_='price_color').text
        rating = product.find('p', class_='star-rating')['class'][1]

        writer.writerow([name, price, rating])

print("✅ Data saved to products.csv")