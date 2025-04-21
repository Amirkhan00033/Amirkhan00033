from flask import Flask, render_template, request, redirect

app = Flask(__name__)

# Хранилище сообщений (пока временно в оперативке)
messages = []

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'POST':
        username = request.form.get('username')
        text = request.form.get('message')
        if username and text:
            messages.append(f"{username}: {text}")
        return redirect('/')
    return render_template('index.html', messages=messages)

if __name__ == '__main__':
    app.run(debug=True)
