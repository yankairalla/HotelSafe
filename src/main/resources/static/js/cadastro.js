const form = document.getElementById('hotelForm');
const cnpjInput = document.getElementById('cnpj');
const telefoneInput = document.getElementById('telefone');
const fotosInput = document.getElementById('fotos');
const dropZone = document.getElementById('dropZone');
const photoPreview = document.getElementById('photoPreview');
const photoCounter = document.getElementById('photoCounter');
const photoCount = document.getElementById('photoCount');
const resultado = document.getElementById('resultado');
const dadosHotel = document.getElementById('dadosHotel');

let selectedFiles = [];

cnpjInput.addEventListener('input', function () {
    let value = this.value.replace(/\D/g, '');
    value = value.replace(/^(\d{2})(\d)/, '$1.$2');
    value = value.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
    value = value.replace(/\.(\d{3})(\d)/, '.$1/$2');
    value = value.replace(/(\d{4})(\d)/, '$1-$2');
    this.value = value;
});

telefoneInput.addEventListener('input', function () {
    let value = this.value.replace(/\D/g, '');
    if (value.length <= 10) {
        value = value.replace(/^(\d{2})(\d)/g, '($1) $2');
        value = value.replace(/(\d)(\d{4})$/, '$1-$2');
    } else {
        value = value.replace(/^(\d{2})(\d)/g, '($1) $2');
        value = value.replace(/(\d)(\d{4})$/, '$1-$2');
    }
    this.value = value;
});

function validarCNPJ(cnpj) {
    cnpj = cnpj.replace(/[^\d]/g, '');
    return cnpj.length === 14;
}

function validarTelefone(telefone) {
    const numeroLimpo = telefone.replace(/\D/g, '');
    return numeroLimpo.length >= 10 && numeroLimpo.length <= 11;
}

function updatePhotoCounter() {
    photoCount.textContent = selectedFiles.length;
    if (selectedFiles.length > 0) {
        photoCounter.classList.remove('hidden');
    } else {
        photoCounter.classList.add('hidden');
    }
}

function createPhotoPreview(file, index) {
    const reader = new FileReader();
    reader.onload = function (e) {
        const previewDiv = document.createElement('div');
        previewDiv.className = 'relative group';
        previewDiv.innerHTML = `
                    <img src="${e.target.result}" alt="Preview" class="w-full h-24 object-cover rounded-lg border">
                    <button type="button" onclick="removePhoto(${index})"
                            class="absolute -top-2 -right-2 bg-red-500 text-white rounded-full w-6 h-6 flex items-center justify-center text-sm hover:bg-red-600 transition duration-200">
                        ×
                    </button>
                    <div class="absolute bottom-0 left-0 right-0 bg-black bg-opacity-50 text-white text-xs p-1 rounded-b-lg truncate">
                        ${file.name}
                    </div>
                `;
        photoPreview.appendChild(previewDiv);
    };
    reader.readAsDataURL(file);
}

function displayPhotos() {
    photoPreview.innerHTML = '';
    if (selectedFiles.length > 0) {
        photoPreview.classList.remove('hidden');
        selectedFiles.forEach((file, index) => {
            createPhotoPreview(file, index);
        });
    } else {
        photoPreview.classList.add('hidden');
    }
    updatePhotoCounter();
}

function removePhoto(index) {
    selectedFiles.splice(index, 1);
    displayPhotos();
}

function validateFile(file) {
    const validTypes = ['image/jpeg', 'image/jpg', 'image/png'];
    const maxSize = 5 * 1024 * 1024; // 5MB

    if (!validTypes.includes(file.type)) {
        alert(`Arquivo ${file.name} não é uma imagem válida. Use apenas JPG, JPEG ou PNG.`);
        return false;
    }

    if (file.size > maxSize) {
        alert(`Arquivo ${file.name} é muito grande. O tamanho máximo é 5MB.`);
        return false;
    }

    return true;
}

// Event listeners para upload de fotos
dropZone.addEventListener('click', () => fotosInput.click());

dropZone.addEventListener('dragover', (e) => {
    e.preventDefault();
    dropZone.classList.add('border-blue-500', 'bg-blue-50');
});

dropZone.addEventListener('dragleave', (e) => {
    e.preventDefault();
    dropZone.classList.remove('border-blue-500', 'bg-blue-50');
});

dropZone.addEventListener('drop', (e) => {
    e.preventDefault();
    dropZone.classList.remove('border-blue-500', 'bg-blue-50');

    const files = Array.from(e.dataTransfer.files);
    const validFiles = files.filter(validateFile);
    selectedFiles.push(...validFiles);
    displayPhotos();
});

fotosInput.addEventListener('change', (e) => {
    const files = Array.from(e.target.files);
    const validFiles = files.filter(validateFile);
    selectedFiles.push(...validFiles);
    displayPhotos();
});

// Tornar removePhoto global
window.removePhoto = removePhoto;

// Submeter formulário
form.addEventListener('submit', function (e) {
    e.preventDefault();

    const formData = new FormData(form);
    const cnpj = formData.get('cnpj');
    const telefone = formData.get('telefone');

    // Validações
    if (!validarCNPJ(cnpj)) {
        alert('Por favor, insira um CNPJ válido com 14 dígitos.');
        return;
    }

    if (!validarTelefone(telefone)) {
        alert('Por favor, insira um telefone válido.');
        return;
    }

    const hotel = {
        nome: formData.get('nomeHotel'),
        endereco: formData.get('endereco'),
        cidade: formData.get('cidade'),
        cnpj: cnpj,
        telefone: telefone,
        checkIn: formData.get('checkIn'),
        checkOut: formData.get('checkOut')
    };

    const formatarHorario = (horario) => {
        const [hora, minuto] = horario.split(':');
        return `${hora}:${minuto}h`;
    };

    dadosHotel.innerHTML = `
                <div class="grid grid-cols-1 md:grid-cols-2 gap-3">
                    <div><strong class="text-gray-700">Nome:</strong> <span class="text-gray-900">${hotel.nome}</span></div>
                    <div><strong class="text-gray-700">Cidade:</strong> <span class="text-gray-900">${hotel.cidade}</span></div>
                    <div class="md:col-span-2"><strong class="text-gray-700">Endereço:</strong> <span class="text-gray-900">${hotel.endereco}</span></div>
                    <div><strong class="text-gray-700">CNPJ:</strong> <span class="text-gray-900">${hotel.cnpj}</span></div>
                    <div><strong class="text-gray-700">Telefone:</strong> <span class="text-gray-900">${hotel.telefone}</span></div>
                    <div><strong class="text-gray-700">Check-in:</strong> <span class="text-blue-600">${formatarHorario(hotel.checkIn)}</span></div>
                    <div><strong class="text-gray-700">Check-out:</strong> <span class="text-red-600">${formatarHorario(hotel.checkOut)}</span></div>
                    <div class="md:col-span-2"><strong class="text-gray-700">Fotos:</strong> <span class="text-green-600">${selectedFiles.length} foto(s) selecionada(s)</span></div>
                </div>
            `;

    resultado.classList.remove('hidden');
    resultado.scrollIntoView({behavior: 'smooth'});
});

// Limpar formulário
form.addEventListener('reset', function () {
    resultado.classList.add('hidden');
    selectedFiles = [];
    photoPreview.innerHTML = '';
    photoPreview.classList.add('hidden');
    photoCounter.classList.add('hidden');
});

document.getElementById('checkIn').value = '14:00';
document.getElementById('checkOut').value = '12:00';

let roomCounter = 0;

const addRoomBtn = document.getElementById('addRoomBtn');
const roomsContainer = document.getElementById('roomsContainer');
const noRoomsMessage = document.getElementById('noRoomsMessage');

function toggleNoRoomsMessage() {
    const hasRooms = roomsContainer.children.length > 0;
    noRoomsMessage.style.display = hasRooms ? 'none' : 'block';
}

function createRoomElement() {
    roomCounter++;

    const roomDiv = document.createElement('div');
    roomDiv.className = 'bg-gray-50 p-6 rounded-lg border border-gray-200 relative';
    roomDiv.dataset.roomId = roomCounter;

    roomDiv.innerHTML = `
                <div class="flex justify-between items-center mb-4">
                    <h3 class="text-lg font-medium text-gray-700">Quarto ${roomCounter}</h3>
                    <button type="button" class="remove-room text-red-500 hover:text-red-700 p-1 rounded-full hover:bg-red-50 transition duration-200" data-room-id="${roomCounter}">
                        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
                        </svg>
                    </button>
                </div>

                <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-2">Nome do Quarto</label>
                        <input type="text" name="rooms[${roomCounter}][name]" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Ex: Suíte Deluxe" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-2">Capacidade Máxima</label>
                        <input type="number" name="rooms[${roomCounter}][capacity]" min="1" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Ex: 2" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-2">Quantidade</label>
                        <input type="number" name="rooms[${roomCounter}][quantity]" min="1" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Ex: 5" required>
                    </div>

                    <div>
                        <label class="block text-sm font-medium text-gray-700 mb-2">Preço (R$)</label>
                        <input type="number" name="rooms[${roomCounter}][price]" min="0" step="0.01" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent" placeholder="Ex: 150.00" required>
                    </div>

                    <div class="md:col-span-2">
                        <label class="block text-sm font-medium text-gray-700 mb-2">Descrição</label>
                        <textarea name="rooms[${roomCounter}][description]" rows="3" class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent resize-none" placeholder="Descreva as características e comodidades do quarto..." required></textarea>
                    </div>
                </div>
            `;

    return roomDiv;
}

function addRoom() {
    const roomElement = createRoomElement();
    roomsContainer.appendChild(roomElement);
    toggleNoRoomsMessage();

    // Scroll suave para o novo quarto
    roomElement.scrollIntoView({behavior: 'smooth', block: 'center'});

    // Adicionar event listener para o botão de remover
    const removeBtn = roomElement.querySelector('.remove-room');
    removeBtn.addEventListener('click', function () {
        removeRoom(this.dataset.roomId);
    });
}

function removeRoom(roomId) {
    const roomElement = document.querySelector(`[data-room-id="${roomId}"]`);
    if (roomElement) {
        roomElement.style.transform = 'scale(0.95)';
        roomElement.style.opacity = '0';
        roomElement.style.transition = 'all 0.3s ease';

        setTimeout(() => {
            roomElement.remove();
            toggleNoRoomsMessage();
            updateRoomNumbers();
        }, 300);
    }
}

function updateRoomNumbers() {
    const rooms = roomsContainer.children;
    for (let i = 0; i < rooms.length; i++) {
        const roomTitle = rooms[i].querySelector('h3');
        roomTitle.textContent = `Quarto ${i + 1}`;
    }
}

// Event Listeners
addRoomBtn.addEventListener('click', addRoom);

// Form submission
document.getElementById('hotelForm').addEventListener('submit', function (e) {
    e.preventDefault();

    const formData = new FormData(this);
    const data = {};

    // Processar dados do formulário
    for (let [key, value] of formData.entries()) {
        if (key.includes('rooms[')) {
            // Processar dados dos quartos
            if (!data.rooms) data.rooms = [];

            const match = key.match(/rooms\[(\d+)\]\[(\w+)\]/);
            if (match) {
                const roomIndex = match[1];
                const field = match[2];

                if (!data.rooms[roomIndex]) {
                    data.rooms[roomIndex] = {};
                }
                data.rooms[roomIndex][field] = value;
            }
        } else {
            data[key] = value;
        }
    }

    // Filtrar quartos vazios
    if (data.rooms) {
        data.rooms = data.rooms.filter(room => room);
    }

    console.log('Dados do formulário:', data);
});

toggleNoRoomsMessage();