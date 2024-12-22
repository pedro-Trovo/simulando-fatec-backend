import os
import json
import requests

API_URL_QUESTAO = "http://localhost:8080/api/questao"

def enviar_questao(dados):
    try:
        response = requests.post(API_URL_QUESTAO, json=dados)
        if response.status_code == 201:
            print("Questão enviada com sucesso!")
        else:
            print(f"Falha ao enviar questão. Código de status: {response.status_code}")
    except Exception as e:
        print(f"Erro ao enviar a questão: {e}")

def processar_arquivo_json(caminho_arquivo):
    try:
        with open(caminho_arquivo, 'r', encoding='utf-8') as arquivo:
            dados = json.load(arquivo)
        enviar_questao(dados)
    except Exception as e:
        print(f"Erro ao processar o arquivo {caminho_arquivo}: {e}")

def processar_pastas(diretorio_base):
    for root, dirs, files in os.walk(diretorio_base):
        for file in files:
            if file.endswith(".json"):
                caminho_arquivo = os.path.join(root, file)
                print(f"Processando arquivo: {caminho_arquivo}")
                processar_arquivo_json(caminho_arquivo)

if __name__ == "__main__":
    diretorio_base = "vestibulares"
    if os.path.exists(diretorio_base):
        processar_pastas(diretorio_base)
    else:
        print(f"Diretório {diretorio_base} não encontrado.")
