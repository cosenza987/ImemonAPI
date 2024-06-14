import requests
import mysql.connector
from mysql.connector import Error

def fetch_pokemon_data(pokemon_id):
    url = f"https://pokeapi.co/api/v2/pokemon/{pokemon_id}/"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error fetching data for Pokémon ID {pokemon_id}")
        return None
    
def fetch_pokemon_name(pokemon_data):
    url = pokemon_data['species']['url']
    response = requests.get(url)
    if response.status_code == 200:
        species = response.json()
        name = ""
        for i in species['names']:
            if i['language']['name'] == 'en':
                name = i['name']
                break
        return name
    else:
        print(f"Error fetching data for Pokémon ID {pokemon_data['id']}")
        return None

def fetch_pokemon_flavor(pokemon_data):
    url = pokemon_data['species']['url']
    response = requests.get(url)
    if response.status_code == 200:
        species = response.json()
        name = ""
        for i in species['flavor_text_entries']:
            if i['language']['name'] == 'en':
                name = i['flavor_text']
                break
        return name
    else:
        print(f"Error fetching flavor for Pokémon ID {pokemon_data['id']}")
        return None
    
def fetch_type_data(type_id):
    url = f"https://pokeapi.co/api/v2/type/{type_id}/"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error fetching data for type ID {type_id}")
        return None

def fetch_ability_data(ability_id):
    url = f"https://pokeapi.co/api/v2/ability/{ability_id}/"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error fetching data for ability ID {ability_id}")
        return None
    
def fetch_move_data(move_id):
    url = f"https://pokeapi.co/api/v2/move/{move_id}/"
    response = requests.get(url)
    if response.status_code == 200:
        return response.json()
    else:
        print(f"Error fetching data for move ID {move_id}")
        return None

def insert_ability(connection, ability):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO ability (id, name, effect) VALUES (%s, %s, %s)"""
        name = ""
        for i in ability['names']:
            if i['language']['name'] == 'en':
                name = i['name']
                break
        short_effect = ""
        for i in ability['effect_entries']:
            if i['language']['name'] == 'en':
                short_effect = i['short_effect']
                break
        insert_tuple = (ability['id'], name, short_effect)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Ability {ability['name']} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert ability {ability['name']} into MySQL table: {error}")
    finally:
        cursor.close()

def insert_move(connection, move):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO move (id, name, effect) VALUES (%s, %s, %s)"""
        name = ""
        for i in move['names']:
            if i['language']['name'] == 'en':
                name = i['name']
                break
        short_effect = ""
        for i in move['effect_entries']:
            if i['language']['name'] == 'en':
                short_effect = i['short_effect']
                break
        insert_tuple = (move['id'], name, short_effect)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Move {move['name']} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert move {move['name']} into MySQL table: {error}")
    finally:
        cursor.close()

def insert_pokemon(connection, pokemon):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO pokemon (id, name, image, total, hp, attack, defense, sp_att, sp_def, speed, flavor) 
                              VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"""
        total = pokemon['stats'][0]['base_stat'] + pokemon['stats'][1]['base_stat'] + pokemon['stats'][2]['base_stat'] + pokemon['stats'][3]['base_stat'] + pokemon['stats'][4]['base_stat'] + pokemon['stats'][5]['base_stat']
        insert_tuple = (pokemon['id'], pokemon['name'], pokemon['sprites']['front_default'],
                        total, 
                        pokemon['stats'][0]['base_stat'], pokemon['stats'][1]['base_stat'], 
                        pokemon['stats'][2]['base_stat'], pokemon['stats'][3]['base_stat'], 
                        pokemon['stats'][4]['base_stat'], pokemon['stats'][5]['base_stat'], fetch_pokemon_flavor(pokemon))
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Pokemon {pokemon['name']} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert Pokémon {pokemon['name']} into MySQL table: {error}")
    finally:
        cursor.close()

def insert_type(connection,type_info):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO type (id, name) VALUES (%s, %s)"""
        name = ""
        for i in type_info['names']:
            if i['language']['name'] == 'en':
                name = i['name']
                break
        insert_tuple = (type_info['id'], name)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Type {type_info['name']} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert type {type_info['name']} into MySQL table: {error}")
    finally:
        cursor.close()

def insert_pokemon_ability(connection, pokemon_id, ability_id):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO pokemon_ability (pokemon_id, ability_id) VALUES (%s, %s)"""
        insert_tuple = (pokemon_id, ability_id)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Pokemon-Ability relationship for Pokémon ID {pokemon_id} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert Pokémon-Ability relationship for Pokémon ID {pokemon_id}: {error}")
    finally:
        cursor.close()

def insert_pokemon_move(connection, pokemon_id, move_id):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO pokemon_move (pokemon_id, move_id) VALUES (%s, %s)"""
        insert_tuple = (pokemon_id, move_id)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Pokemon-Move relationship for Pokémon ID {pokemon_id} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert Pokémon-Move relationship for Pokémon ID {pokemon_id}: {error}")
    finally:
        cursor.close()

def insert_pokemon_type(connection, pokemon_id, type_id):
    cursor = connection.cursor()
    try:
        sql_insert_query = """INSERT INTO pokemon_type (pokemon_id, type_name) VALUES (%s, %s)"""
        name = ""
        type_info = fetch_type_data(type_id)
        for i in type_info['names']:
            if i['language']['name'] == 'en':
                name = i['name']
                break
        insert_tuple = (pokemon_id, name)
        cursor.execute(sql_insert_query, insert_tuple)
        connection.commit()
        print(f"Pokemon-Type relationship for Pokémon ID {pokemon_id} inserted successfully.")
    except mysql.connector.Error as error:
        print(f"Failed to insert Pokémon-Type relationship for Pokémon ID {pokemon_id}: {error}")
    finally:
        cursor.close()

def main():
    try:
        # MySQL database connection
        connection = mysql.connector.connect(
            host='localhost',
            database='imemon',
            user='root',
            password='gremio19'
        )
        if connection.is_connected():
            print("Connected to MySQL database")

            count = 1
            for pokemon_id in range(1, 1303):

                if pokemon_id >= 1026:
                    pokemon_id = 10000 + count
                    count += 1

                pokemon_data = fetch_pokemon_data(pokemon_id)
                pokemon_data['name'] = fetch_pokemon_name(pokemon_data)

                if pokemon_data:
                    insert_pokemon(connection, pokemon_data)

                    for ability in pokemon_data['abilities']:
                        url = ability['ability']['url']
                        id_str = url.split("/")[-2]
                        ability_id = int(id_str)
                        insert_pokemon_ability(connection, pokemon_data['id'], ability_id)

                    for move in pokemon_data['moves']:
                        url = move['move']['url']
                        id_str = url.split("/")[-2]
                        move_id = int(id_str)
                        insert_pokemon_move(connection, pokemon_data['id'], move_id)

                    for type_info in pokemon_data['types']:
                        url = type_info['type']['url']
                        id_str = url.split("/")[-2]
                        type_id = int(id_str)
                        insert_pokemon_type(connection, pokemon_data['id'], type_id)
            count = 1
            for move_id in range(1,938):

                if move_id >= 920:
                    move_id = 10000 + count
                    count += 1

                move_data = fetch_move_data(move_id)
                if move_data:
                    insert_move(connection, move_data)
            
            count = 1
            for ability_id in range(1,368):

                if ability_id >= 308:
                    ability_id = 10000 + count
                    count += 1

                ability_data = fetch_ability_data(ability_id)
                if ability_data:
                    insert_ability(connection, ability_data)
            
            for type_id in range(1,20):

                if type_id == 19: type_id = 10001
                if type_id == 20: type_id = 10002

                type_data = fetch_type_data(type_id)
                if type_data:
                    insert_type(connection, type_data)


    except Error as e:
        print(f"Error connecting to MySQL: {e}")
    finally:
        if connection.is_connected():
            connection.close()
            print("MySQL connection closed")
        print("caralho")

if __name__ == "__main__":
    main()
