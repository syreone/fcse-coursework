cities = {}

while True:
    line = input()
    if line.strip() == "end":
        break
    parts = line.split()
    city, temp, rain = parts[0], float(parts[1]), parts[2]

    if city not in cities:
        cities[city] = {"temps": [], "rain": 0}

    cities[city]["temps"].append(temp)
    if rain == "yes":
        cities[city]["rain"] += 1

sorted_cities = sorted(cities.items(), key=lambda x: (-x[1]["rain"], x[0]))

for city, data in sorted_cities:
    avg_temp = sum(data["temps"]) / len(data["temps"])
    formatted = f"{avg_temp:.2f}".rstrip('0')
    if formatted.endswith('.'):
        formatted += '0'
    print(f"{city} {formatted} {data['rain']}")