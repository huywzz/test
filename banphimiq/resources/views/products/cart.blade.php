@foreach (session()->get('cart') as $key)
    <a href="dashboard-analytics.html" class="dropdown-item"> {{ $key['name'] }} -
        {{ $key['quantity'] }} </a>
@endforeach
