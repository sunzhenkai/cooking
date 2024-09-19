from jinja2 import Template
from pathlib import Path

print(Path.cwd())
with open('template/msg.html') as f:
    template = Template(f.read())
    rendered = template.render(title='message')
    print(rendered)
